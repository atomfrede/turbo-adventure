package de.atomfrede.github.karaoke.server.mongo;

import org.jongo.FindOne;
import org.jongo.MongoCollection;

public class MongoUtil {

    public static <T> T getRandomDocument(MongoCollection c, Class<T> clazz ){
        double x = Math.random();
        T result = null;
        result = c.findOne("{random:{ $gte:" + x + "} }").as(clazz);
        if(result == null){
            result = c.findOne("{random:{ $lte:" + x + "} }").as(clazz);
        }
        return result;
    }
}
