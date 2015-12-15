package de.atomfrede.github.karaoke.server.mongo;
import de.atomfrede.github.karaoke.server.entity.Singer;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import mockit.NonStrictExpectations;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(JMockit.class)
public class MongoUtilTest {

    @Mocked
    MongoCollection mongoCollection;

    @Test
    public void returnsEntity(){

//        new MockUp<Math>(){
//            @Mock
//            @SuppressWarnings("unused")
//            double random(){
//                return 0.9d;
//            }
//        };
//
//        new NonStrictExpectations() {{
//
//            mongoCollection.find(String.format("%s", "{ random : { $gte : " + 0.9d + "}}")).sort("{random: 1}").as(Singer.class);
//            times = 2;
//            result =  null;
//            result =  new Singer("1");
//
//            mongoCollection.find(String.format("%s", "{ random : { $lte : " + 0.9d + "}}")).sort("{random: 1}").as(Singer.class);
//            times = 1;
//            result = new Singer("2");
//        }};
//
//        Singer s = MongoUtil.getRandomDocument(mongoCollection, Singer.class, "");
//        assertThat(s.id().equals("2"), is(true));
//
//        s = MongoUtil.getRandomDocument(mongoCollection, Singer.class, "");
//        assertThat(s.id() == "1", is(true));
    }
}
