package de.atomfrede.github.karaoke.server;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import de.atomfrede.github.karaoke.server.config.KaraokeConfiguration;
import de.atomfrede.github.karaoke.server.mongo.MongoHealthCheck;
import de.atomfrede.github.karaoke.server.mongo.SingerRepository;
import de.atomfrede.github.karaoke.server.mongo.SongRepository;
import de.atomfrede.github.karaoke.server.resource.PairingResource;
import de.atomfrede.github.karaoke.server.resource.PingResource;
import de.atomfrede.github.karaoke.server.resource.SingerResource;
import de.atomfrede.github.karaoke.server.resource.SongResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.java8.Java8Bundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.mongeez.Mongeez;
import org.springframework.core.io.ClassPathResource;

public class KaraokeApplication extends Application<KaraokeConfiguration> {

    public static void main(final String[] args) throws Exception {
        new KaraokeApplication().run(args);
    }

    @Override
    public void initialize(final Bootstrap<KaraokeConfiguration> bootstrap) {

        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(), new EnvironmentVariableSubstitutor(false))
        );
        bootstrap.addBundle(new Java8Bundle());
        bootstrap.addBundle(new AssetsBundle("/ui/", "/ui", "index.html"));
    }

    @Override
    public void run(KaraokeConfiguration configuration, Environment environment) throws Exception {

        MongoClientOptions clientOptions = MongoClientOptions.builder()
                .connectTimeout(500).build();
        ServerAddress serverAddress = new ServerAddress(configuration.mongohost, configuration.mongoport);

        MongoClient mongo = new MongoClient(serverAddress, clientOptions);
        environment.healthChecks().register("MongoDB", new MongoHealthCheck(mongo));

        migrateDatabase(mongo, configuration.mongodb);

        final SingerRepository singerRepository = new SingerRepository(new DB(mongo, configuration.mongodb));
        environment.lifecycle().manage(singerRepository);

        final SongRepository songRepository = new SongRepository(new DB(mongo, configuration.mongodb));
        environment.lifecycle().manage(songRepository);

        final SingerResource singerResource = new SingerResource(singerRepository);
        environment.jersey().register(singerResource);

        final SongResource songResource = new SongResource(songRepository);
        environment.jersey().register(songResource);

        final PairingResource pairingResource = new PairingResource(singerRepository, songRepository);
        environment.jersey().register(pairingResource);

        PingResource pingResource = new PingResource();

        environment.jersey().register(pingResource);
    }

    private void migrateDatabase(MongoClient mongo, String dbName) {

        try {
            Mongeez mongeez = new Mongeez();
            mongeez.setFile(new ClassPathResource("/mongeez/master.xml"));
            mongeez.setMongo(mongo);
            mongeez.setDbName(dbName);
            mongeez.process();
        } catch (Exception e) {

        }

    }

}
