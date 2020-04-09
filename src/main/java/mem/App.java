package mem;

import org.bson.Document;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Database database = Database.getInstance();

        ArtistController artistController = new ArtistController(database.getArtists());
        AlbumController albumController = new AlbumController(database.getAlbums());

        artistController.create("Tom", "England");
        Document artist = artistController.findByName("Tom");

        albumController.create("Security", artist.get("_id").toString(), 1997);
        Document album = albumController.findByArtist(artist.get("_id").toString());

        System.out.println(artist);
        System.out.println(album);

        database.getMongoClient().close();
    }
}
