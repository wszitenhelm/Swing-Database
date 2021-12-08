package CS2020.assignment2;
import java.util.UUID;

/**
* This class creates Song object that has instance variables: 
* UUID songID, UUID artistID, String title and int duration. 
* This public class implements Comparable.
*/
public class Song {
    private UUID songID;
    private UUID artistID;
    private String title;
    private int duration;

/**
 * This method is created to return title of Artist 
 * @return title 
 */
    public String toString() { 
        return title;
    }

/**
 * Method for setting Song's songID.
 */ 
    public void setSongID () {
        this.songID = UUID.randomUUID();
    }

/**
 * Method that returns Song's songID.
 * @return songID
 */ 
    public UUID getSongID() {
        return songID;
    }

/**
 * Method for setting Song's artistID.
 * @param artistID
 */ 
    public void setArtistID (UUID artistID) {
        this.artistID = artistID; 
    }

/**
 * Method that returns Song's artistID.
 * @return artistID
 */ 
    public UUID getArtistID() {
        return artistID;
    }

/**
 * Method for setting Song's title.
 * @param title
 */ 
    public void setTitle (String title) {
        this.title = title;
    }
    
/**
 * Method that returns Song's title.
 * @return title
 */ 
    public String getTitle() {
        return title;
    }

/**
 * Method for setting Song's duration.
 * @param duration
 */ 
    public void setDuration (int duration) {
        this.duration = duration;
    }

/**
 * Method that returns Song's duration.
 * @return duration
 */ 
    public int getDuration() {
        return duration;
    }
}