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

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import static com.mongodb.m101j.util.Helpers.printJson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

public class App {
    public static void main(String[] args) {
        MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        MongoDatabase database = client.getDatabase("blog");
        MongoCollection<Document> collection = database.getCollection("posts");
        
        long num = collection.count();
        System.out.println(num);        
        FindIterable<Document> list = collection.find();
                       
//        printJson(list.first());
        
        
        
//        System.out.println("Find one:");
//        Document first = collection.find().first();
//        printJson(first);
//        
        Bson agg = Aggregates.unwind("comments");
          Bson filter = eq("scores.type", "homework");
//          
////          Bson pro = new Document("scores.score", 1).append("scores.type", "homework");
          Bson pro1 = Projections.fields(Projections.include("scores.score"));
        
        AggregateIterable<Document> aggre = collection.aggregate(Arrays.asList(new Document("$unwind", "$comments"),new Document("$group", new Document("_id", "$comments.author").append("count", new Document("$sum", 1))), new Document("$sort", new Document("count", -1))));
////        
//        AggregateIterable<Document> aggre = collection.aggregate(Arrays.asList(agg));
//                             
//          List<Document> myList = new LinkedList<Document>();
//          
//          List<Double> scoresList = new LinkedList<Double>();
//          
          for (Document myDoc : aggre){
              printJson(myDoc);
//              myList.add(myDoc);              
          }
//          
//          System.out.println(myList.size());
//                    
//          for (Document myDoc1 : myList){
////              System.out.println("mydoc" + myDoc1);
//              Document doc3 = (Document) myDoc1.get("scores");
////              System.out.println("doc3 " + doc3);
//              String helper = doc3.get("score").toString();
////              System.out.println("helper " + helper);
//              double a = Double.valueOf(helper);
////              System.out.println("a " + a);
//              scoresList.add(a);
//          }
//          
//          System.out.println(scoresList.size());
//          
////          for ( Document doc : myList){
////              System.out.println(doc);
////          }
//          
////          for (double b : scoresList){
////              System.out.println(b);
////          }
//          
//          List<Double> finalList = new LinkedList<Double>();
//          List<Double> finalList1 = new LinkedList<Double>();          
//          
//          for (int j = 0; j < scoresList.size(); j++){
//              finalList.add(scoresList.get(j));
//              System.out.println("j "+ j);
//              j++;
//          }
////          
//          for (int k = 1; k < scoresList.size(); k++){
//              finalList1.add(scoresList.get(k));
//              k++;
//          }
//          
////          for (int i = 0; i < scoresList.size()-1; i++){
//////              System.out.println("i = " + i);
////              if (scoresList.get(i) < scoresList.get(i+1)){
////                  finalList.add(scoresList.get(i));                  
//////                  System.out.println("1");
////              } else {
////                  finalList.add(scoresList.get(i+1));
//////                  System.out.println("2");
////              }
////              i = i + 1;
////          }
//          
//          List<Double> theList = new LinkedList<Double>();
//          
//          for (int i = 0; i < finalList.size(); i++){
//              if (finalList.get(i)<finalList1.get(i)){
//                  System.out.println("0 " + finalList.get(i));
//                  theList.add(finalList.get(i));
//              } else {
//                  System.out.println("1 " + finalList1.get(i));
//                  theList.add(finalList1.get(i));
//              }
//          }
//          
//          for (Double d : theList){
//              System.out.println(d);
//          }          
////          for (Double d : finalList){
////              System.out.println("d " + d);
////          }
////          S
////          for (Double d1 : finalList1){
////              System.out.println("d1 " + d1);
////          }
//          
////          System.out.println(finalList.get(0));
//          
////          for (int i = 0; i < finalList.size(); i++){
////            //Bson pull = Updates.pull("scores.score", finalList.get(i));              
////            collection.updateOne(eq("_id",i+1), Updates.pull("scores.score", finalList.get(i)));
////          }      
//          for (int i = 0; i < finalList.size(); i++){
//              BasicDBObject id = new BasicDBObject("_id", i);              
//              BasicDBObject score = new BasicDBObject("score", theList.get(i));
//              BasicDBObject scores = new BasicDBObject("scores", score);
//              BasicDBObject delstring = new BasicDBObject("$pull", scores);
//              System.out.println(id.toString());
//              System.out.println(delstring.toString());
//              collection.updateOne(id, delstring);                    
//              System.out.println("here");
//          }
//          
//          
////          collection.updateOne(eq("_id", 181), Updates.pull("scores.score", 58.58116890946589));
//          
////          for (double c : finalList){
////              System.out.println(c);
////          }
////          System.out.println(finalList.size());
//          
////          for (Document myDoc1 : myList){
////              printJson(myDoc1);
////          }
////          System.out.println(myList.get(0).get(new Document()));
////          printJson(myList.get(0));
//          
////          System.out.println(myList.get(0).keySet());
////          Document doc2 = (Document) myList.get(0).get("scores");
////          System.out.println(doc2.get("score"));
////          printJson(doc2);
////          System.out.println(myList.get(0).entrySet().contains("score"));
//          
////          System.out.println(aggre.first());
////          Bson filter1 = new Document("type", "homework");
////          Document myDoc = aggre.first();
//          
////            printJson(aggre.first());
////        
////          List<Document> all = collection.find(filter)
////                                        .projection(pro)
////                                        .into(new ArrayList<Document>());
////          System.out.println(all.get(0).values());
////          
//        List<Document> all2 = new ArrayList<Document>();
//
////            for (Document cur : all) {
////                printJson(cur);                
////            }
////            System.out.println(all.size());
//    //        
////        for (int i = 0; i < all.size(); i++){
////            if (i%2 != 0){
////                all2.add(all.get(i));
////            }
////        }
//////        
////        for (Document cur1 : all2){
////            //printJson(cur1);
////            collection.deleteOne(cur1);
////        }
//        
//        
////            
////        
////        long count = collection.count();
////        System.out.println(count);
        
    }
}
