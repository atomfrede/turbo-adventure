package de.atomfrede.github.karaoke.server.mongo;

import org.jongo.FindOne;
import org.jongo.MongoCollection;

public class MongoUtil {

    public static <T> T getRandomDocument(MongoCollection c, Class<T> clazz ){
        double x = Math.random();
        T result = null;
        //Find nearest greater random attribute value in collection c.
        result = c.findOne("{random:{ $gte:" + x + "} }").as(clazz);
        if(result == null){
            //If no greater value was found (random double bigger than anything in the collection).
            //Find nearest lesser random attribute value in collection c.
            result = c.findOne("{random:{ $lte:" + x + "} }").as(clazz);
        }
        return result;
    }
}
