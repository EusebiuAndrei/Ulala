package mem;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class ArtistController {
    private MongoCollection<Document> collection;

    public ArtistController(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    public void create(String name, String country) {
        Document doc = new Document("name", name)
                .append("country", country);

        collection.insertOne(doc);
    }

    public Document findByName(String name) {
        return collection.find(eq("name", name)).first();
    }
}
