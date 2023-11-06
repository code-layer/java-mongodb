package in.datalayer.mongodb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import in.datalayer.mongodb.models.Cinema;
import in.datalayer.mongodb.models.Country;
import in.datalayer.mongodb.models.Customer;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Application {
    public static void main(String[] args) throws JsonProcessingException {
        MongoClient mc = MongoConfig.getClient();
        MongoDatabase md = mc.getDatabase("mymongo");
        MongoCollection<Document> collection = md.getCollection("test");

        Customer c = prepareCustomer();
        Integer _id = addDocument(collection,c);

        Document document = getDocument(collection, _id);
        System.out.println("Mongo document: " + document);

        Customer customer = getCustomer(document);
        System.out.println("Mongo document to pojo Customer: " + customer);
    }

    public static Customer prepareCustomer() {
        Customer c = new Customer();

        //change value after each run
        c.set_id(1010);

        c.setName("Ram Kumar");
        c.setInstant(Instant.now());
        c.setJoinDate(new Date());
        c.setScore(935739.485);
        List<String> hs = new ArrayList<>();
        hs.add("music");
        hs.add("cricket");
        c.setHobbies(hs);

        Country ctr = new Country();
        ctr.setName("India");
        ctr.setCode("AP-IN");
        c.setCountry(ctr);

        List<Cinema> cinemas = new ArrayList<>();
        Cinema cin1 = new Cinema();
        cin1.setName("Gandhi");
        cin1.setReleaseYear(1986);
        cinemas.add(cin1);
        c.setCinemas(cinemas);

        return c;
    }

    public static Integer addDocument(MongoCollection<Document> collection, Customer c) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String json =  mapper.writeValueAsString(c);
        collection.insertOne(Document.parse(json));
        return c.get_id();
    }

    public static Document getDocument(MongoCollection<Document> collection, Integer _id) throws JsonProcessingException {
        Bson filter = Filters.eq("_id", _id);
        FindIterable<Document> fi = collection.find(filter);
        Document document = fi.first();
        return document;
    }

    public static Customer getCustomer(Document document) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        Customer customer = mapper.readValue(document.toJson(), Customer.class);
        return customer;
    }

}