package mem;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class AlbumController {
    private MongoCollection<Document> collection;

    public AlbumController(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    public void create(String name, String artistId, int releaseYear) {
        Document doc = new Document("name", name)
                .append("artistId", artistId)
                .append("releaseYear", releaseYear);

        collection.insertOne(doc);
    }

    public Document findByArtist(String artistId) {
        return collection.find(eq("artistId", artistId)).first();
    }
}
