package com.db.mongodb;

//import com.mongodb.MongoClient;
//import com.mongodb.client.FindIterable;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import org.bson.Document;
//
///**
// * Created by 大胡子Kevin on 2016/5/29.
// */
//public class MongoDBJDBC {
//    static MongoClient mongoClient;
//    static MongoDatabase db;
//    static MongoCollection<Document> collection;
//    static {
//        // 连接到 mongodb 服务
////        mongoClient = new MongoClient( "121.41.10.2" , 27017 );
//        // 连接到数据库
////        db = mongoClient.getDatabase("test");
////        db.createCollection("collections");
//        collection = db.getCollection("collections");
//    }
//
//    public static void main( String args[] ){
//        System.out.println("Connect to database successfully");
//        System.out.println(collection);
//        find();
//    }
//
//    static void create() {
//        Document document = new Document();
//        document.append("id", 123).append("name", "foo").append("age", 22);
//        collection.insertOne(document);
//    }
//    static void find() {
//        FindIterable<Document> documents = collection.find();
//        for (Document doc : documents) {
//            System.out.println(doc);
//        }
//    }
//
//}
