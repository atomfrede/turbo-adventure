package de.atomfrede.github.karaoke.server.entity;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;

public class SingersTest {

    @Test
    public void assertThatSingersAreAdded() {

        Singers singers = new Singers();

        Singer johnDoe = new Singer().setFirstname("John").setLastname("Doe");
        Singer janeDoe = new Singer().setFirstname("Jane").setLastname("Doe");

        List<Singer> listOfSingers = Arrays.asList(johnDoe, janeDoe);

        singers.setSingers(listOfSingers);

        assertThat(singers.getSingers(), hasItems(janeDoe, johnDoe));
    }
}
