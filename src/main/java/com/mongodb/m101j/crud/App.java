/*
 * Copyright 2015 MongoDB, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mongodb.m101j.crud;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import static com.mongodb.m101j.util.Helpers.printJson;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

public class App {
    public static void main(String[] args) {
        MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        MongoDatabase database = client.getDatabase("video");
        MongoCollection<Document> collection = database.getCollection("movieDetails");
        
//        System.out.println("Find one:");
//        Document first = collection.find().first();
//        printJson(first);
//        
          Bson filter = eq("countries.1", "Sweden");
          
          Bson pro = Projections.slice("countries", 1, 1);
//        
          
//        
          List<Document> all = collection.find(filter)
                                        .into(new ArrayList<Document>());
          
//        List<Document> all2 = new ArrayList<Document>();
//
            for (Document cur : all) {
                printJson(cur);                
            }
            System.out.println(all.size());
    //        
//        for (int i = 0; i < all.size(); i++){
//            if (i%2 != 0){
//                all2.add(all.get(i));
//            }
//        }
//        
//        for (Document cur1 : all2){
//            //printJson(cur1);
//            collection.deleteOne(cur1);
//        }
        
        
            
        
        long count = collection.count();
        System.out.println(count);
        
    }
}
