package de.atomfrede.github.karaoke.server.mongo;

import com.mongodb.DB;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.integration.junit4.JMockit;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(JMockit.class)
public class SingerRepositoryTest {

    @Mocked
    DB database;
    @Mocked
    MongoCollection mongoCollection;
    @Mocked
    Jongo jongo;

    SingerRepository singerRepository;

    @Before
    public void setup() {
        singerRepository = new SingerRepository(database);
    }

    @Test
    public void assertThatCountIsReturned() {

        new NonStrictExpectations() {{

            mongoCollection.count();
            times = 1;
            result = 42;
        }};

        assertThat(singerRepository.count(), is(42L));
    }
}
