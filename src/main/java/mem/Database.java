package mem;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.ValidationOptions;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

public class Database {
    private static String DB_CONNECTION = "mongodb+srv://dbUser:dbuser123@cluster0-p8hhy.mongodb.net/test?w=majority";
    private static Database dbInstance = null;

    private com.mongodb.client.MongoClient mongoClient;
    private MongoCollection<Document> artists;
    private MongoCollection<Document> albums;

    private Database() {
        ConnectionString connString = new ConnectionString(DB_CONNECTION);
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connString)
                .retryWrites(true)
                .build();
        mongoClient = MongoClients.create(settings);

        init();
    }

    public static Database getInstance() {
        if (dbInstance == null) {
            dbInstance = new Database();
        }

        return dbInstance;
    }

    public void init() {
        MongoDatabase db = mongoClient.getDatabase("MusicAlbums");

        artists = db.getCollection("Artists");
        albums = db.getCollection("Albums");
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public MongoCollection<Document> getArtists() {
        return artists;
    }

    public MongoCollection<Document> getAlbums() {
        return albums;
    }
}
