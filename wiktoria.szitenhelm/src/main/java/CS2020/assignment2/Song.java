package CS2020.assignment2;
import java.util.UUID;

public class Song {
    private UUID songID;
    private UUID artistID;
    private String title;
    private int duration;
   
    //Song (UUID songID) {}
    
    public void setSongID (UUID songID) {
        // shouldn't be ? 
        // this.songID = UUID.randomUUID();
        this.songID = songID;
    }
    public UUID getSongID() {
        return songID;
    }
    
    public void setArtistID (UUID artistID) {
        // should't be
        // // this.artistID = UUID.randomUUID();
        this.artistID = artistID; 
    }
    public UUID getArtistID() {
        return artistID;
    }
    
    public void setTitle (String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
    
    public void setDuration (int duration) {
        this.duration = duration;
    }
    public int getDuration() {
        return duration;
    }
}