package de.atomfrede.github.karaoke.server.mongo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.Arrays;

import com.mongodb.DB;
import de.atomfrede.github.karaoke.server.entity.Song;
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
public class SongRepositoryTest {

	@Mocked
	DB database;
	@Mocked
	MongoCollection mongoCollection;
	@Mocked
	Jongo jongo;

	SongRepository songRepository;

	@Before
	public void setUp() {

		//Initialisierung der Instanzvariablen
		songRepository = new SongRepository(database);
	}

	@Test
	public void assertThatCountIsReturned() {

		new NonStrictExpectations() {{//einen bestimmten Wert zurückgeben

			// Methode count der Klasse mongoCollection wird initialisiert und genau einmal ausgeführt
			mongoCollection.count();
			times = 1; result = 24;
		}};

		assertThat(songRepository.count(), is(24L));
		// assertThat (x, is(24)) 
	}

	@Test
	public void assertThatEntityIsRemovedById() {

		new NonStrictExpectations() {{

			mongoCollection.remove("{_id:#}", new ObjectId("507f191e810c19729de860ea"));
			times = 1;
		}};

		songRepository.delete("507f191e810c19729de860ea");
		// deleteMethode aufrufen, id 24 löschen
	}

	@Test
	public void assertThatEntitiesAreRemovedById() {

		new NonStrictExpectations() {{

			mongoCollection.remove("{_id:#}", "507f191e810c19729de860ea");
			times = 1;

			mongoCollection.remove("{_id:#}", "507f191e810c19729de860eb");
			times = 1;
		}};

		Song SongA = new Song("507f191e810c19729de860ea").setTitle("SongA").setInterpreter("InterpreterA");
		Song SongB = new Song("507f191e810c19729de860eb").setTitle("SongA").setInterpreter("InterpreterB");

		songRepository.delete(Arrays.asList(SongA, SongB));
	}

	@Test
	public void assertThatAllEntitiesAreRemoved() {

		new NonStrictExpectations() {{

			// Drops this collection from the Database - verwirft die gesamte Sammlung
			mongoCollection.drop();
			times = 1;
		}};

		songRepository.deleteAll();
	}
	
	@Test
	public void assertThatOneCanBeFound() {

		new NonStrictExpectations() {{

			// Returns one document that satisfies the specified query criteria
			mongoCollection.findOne("{_id:#}", new ObjectId("507f191e810c19729de860ea")).as(Song.class);
			times = 1; result = new Song("24");

			mongoCollection.findOne("{_id:#}", new ObjectId("507f191e810c19729de860eb")).as(Song.class);
			times = 1; result = null;
		}};

		assertThat(songRepository.exists("507f191e810c19729de860ea"), is(true));
		assertThat(songRepository.exists("507f191e810c19729de860eb"), is(false));
	}
	
}
