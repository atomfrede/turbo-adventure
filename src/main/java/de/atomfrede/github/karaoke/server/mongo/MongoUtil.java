package de.atomfrede.github.karaoke.server.mongo;

import org.jongo.FindOne;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;

public class MongoUtil {

    public static <T> T getRandomDocument(MongoCollection c, Class<T> clazz, String filter ){
        if(c.count() <= 0){
            return null;
        }

        String query = "%s";
        if( filter != null && !filter.equals("") ){
            query = "{$and: ["+filter+", %s]}";
        }
        double x = Math.random();

        //Find nearest greater random attribute value in collection c.
        MongoCursor<T> results = c.find(String.format(query, "{ random : { $gte : " + x + "}}")).sort("{random: 1}").as(clazz);
        if(results.count() < 1){
            //If no greater value was found (random double bigger than anything in the collection).
            //Find nearest lesser random attribute value in collection c.
            results = c.find(String.format(query, "{ random : { $lte : " + x + "}}")).sort("{random: -1}").as(clazz);
        }
        return results.next();
    }


    public static <T> T getRandomDocument(MongoCollection c, Class<T> clazz ) {
        return MongoUtil.getRandomDocument(c, clazz, null);
    }
}
