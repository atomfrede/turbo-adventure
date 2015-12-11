package de.atomfrede.github.karaoke.server.resource;

import de.atomfrede.github.karaoke.server.entity.Singer;
import de.atomfrede.github.karaoke.server.entity.Song;
import de.atomfrede.github.karaoke.server.entity.Triple;
import de.atomfrede.github.karaoke.server.mongo.PairingRepository;
import de.atomfrede.github.karaoke.server.mongo.SingerRepository;
import de.atomfrede.github.karaoke.server.mongo.SongRepository;

import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.integration.junit4.JMockit;

import org.bson.types.ObjectId;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(JMockit.class)
public class PairingResourceTest {


    @Mocked
    SingerRepository singerRepository;

    @Mocked
    SongRepository songRepository;

    @Mocked
    PairingRepository pairingRepository;

    PairingResource pairingResource;

    @Before
    public void setup() {
        pairingResource = new PairingResource(singerRepository,songRepository,pairingRepository);
    }

    @Test
    public void assertThatMatchingPairIsFound() {

        new NonStrictExpectations() {{

            singerRepository.getRandomPair(false, false);
            times = 1;
            result = new Singer[]{new Singer(), new Singer()};

            songRepository.getRandom();
            times = 1;
            result = new Song();
        }};

        Triple t = pairingResource.generatePairing();
        assertThat( t == null, is(false));
    }

    @Test
    public void assertThatNoMatchingPairIsFound() {

        new NonStrictExpectations() {{

            singerRepository.getRandomPair(false, false);
            times = 1;
            result = null;
        }};

        Triple t = pairingResource.generatePairing();
        assertThat(t == null, is(true));
    }

    @Test
    public void assertThatUniquePairIsFound() {

        new NonStrictExpectations() {{

            singerRepository.getRandomPair(false, false);
            times = 1;
            result = new Singer[]{new Singer("1"), new Singer("2")};

            songRepository.getRandom();
            times = 1;
            result = new Song();
        }};

        Triple t = pairingResource.generatePairing();
        assertThat( t.left().id().equals(t.right().id()), is(false));
    }
}
