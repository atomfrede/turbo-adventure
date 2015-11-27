package de.atomfrede.github.karaoke.server.mongo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.Arrays;

import com.mongodb.DB;
import de.atomfrede.github.karaoke.server.entity.Singer;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.integration.junit4.JMockit;
import org.bson.types.ObjectId;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

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

    @Test
    public void assertThatEntityIsRemovedById() {

        new NonStrictExpectations() {{

            mongoCollection.remove("{_id:#}", new ObjectId("507f191e810c19729de860ea"));
            times = 1;
        }};

        singerRepository.delete("507f191e810c19729de860ea");
    }

    @Test
    public void assertThatEntitiesAreRemovedById() {

        new NonStrictExpectations() {{

            mongoCollection.remove("{_id:#}", new ObjectId("507f191e810c19729de860ea"));
            times = 1;

            mongoCollection.remove("{_id:#}", new ObjectId("507f191e810c19729de860eb"));
            times = 1;
        }};

        Singer janeDoe = new Singer("507f191e810c19729de860ea")
                .setFirstname("Jane")
                .setLastname("Doe");

        Singer johnDoe = new Singer("507f191e810c19729de860eb")
                .setFirstname("John")
                .setLastname("Doe");

        singerRepository.delete(Arrays.asList(janeDoe, johnDoe));
    }

    @Test
    public void assertThatAllEntitiesAreRemoved() {

        new NonStrictExpectations() {{

            mongoCollection.drop();
            times = 1;
        }};

        singerRepository.deleteAll();
    }

    @Test
    public void assertThatOneCanBeFound() {

        new NonStrictExpectations() {{

            mongoCollection.findOne("{_id:#}", new ObjectId("507f191e810c19729de860ea")).as(Singer.class);
            times = 1;
            result = new Singer("42");

            mongoCollection.findOne("{_id:#}", new ObjectId("507f191e810c19729de860eb")).as(Singer.class);
            times = 1;
            result = null;
        }};

        assertThat(singerRepository.exists("507f191e810c19729de860ea"), is(true));
        assertThat(singerRepository.exists("507f191e810c19729de860eb"), is(false));
	}

	@Test
	public void assertThatFindOneById(){

		SingerRepository singerRepository = new SingerRepository(database);
		singerRepository.findOne("55dedd2b708db71cb20ca959");
	}

	@Test
	public void assertThatFindAllSinger(){

		SingerRepository singerRepository = new SingerRepository(database);
		singerRepository.findAll();
	}

	@Test
	public void assertThatEntetiesCanbeSaved(){

		SingerRepository singerRepository = new SingerRepository(database);
		singerRepository.save(singerRepository.findOne("55dedd2b708db71cb20ca959"));
		singerRepository.save(singerRepository.findAll());
	}

	@Test
	public void assertThatAllEntitiesCanBeUpdated(){

		SingerRepository singerRepository = new SingerRepository(database);
		singerRepository.update(singerRepository.findAll());

    }
}
