package CS2020.assignment2;
import java.util.ArrayList;
import java.util.UUID;

/**
* This class creates Artist object that has instance variables: 
* UUID artistID, String firstName, String lastName, String dob, 
* String placeOfBirth and ArrayList<Song> songs. 
* This public class implements Comparable.
*/
public class Artist implements Comparable<Artist>{
    private UUID artistID;
    private String firstName;
    private String lastName;
    private String dob;
    private String placeOfBirth;
    private ArrayList<Song> songs;
  
/**
 * This method is created to return full name of Artist 
 * @return firstName and and lastName of the Artist
 */
    public String toString() { 
        return firstName + " " + lastName;
    }

/**
 * This method overrides compareTo() method 
 * to compare one firstName with the other 
 * @param o
 * @return result of comparison 
 */
    @Override
    public int compareTo(Artist o) 
    {
        return this.getFirstName().compareTo( o.getFirstName() );
    }

/**
 * Class constructor that sets random Artist's artistID 
 */
    Artist () {
        this.artistID = UUID.randomUUID();
        this.songs = new ArrayList<Song>();
    }

/**
 * Method for setting ArtistID.
 * @param artistID
 */ 
    public void setArtistID (UUID artistID) {
        this.artistID = artistID;
    }

/**
 * Method that returns Artist's artistID.
 * @return artistID
 */ 
    public UUID getArtistID() {
        return artistID;
    }

/**
 * Method for setting Artist's firstName.
 * @param firstName
 */ 
    public void setFirstName (String firstName) {
        this.firstName = firstName;
    }

/**
 * Method that returns Artist's firstName.
 * @return firstName
 */ 
    public String getFirstName() {
        return firstName;
    }

/**
 * Method for setting Artist's lastName.
 * @param lastName
 */ 
    public void setLastName (String lastName) {
        this.lastName = lastName;
    }

/**
 * Method that returns Artist's lastName.
 * @return lastName
 */ 
    public String getLastName() {
        return lastName;
    }

/**
 * Method for setting Artist's dob.
 * @param dob
 */ 
    public void setDob (String dob) {
        this.dob = dob;
    }

/**
 * Method that returns Artist's dob
 * @return dob
 */ 
    public String getDob() {
        return dob;
    }

/**
 * Method for setting Artist's placeOfBirth.
 * @param placeOfBirth
 */ 
    public void setPlaceOfBirth (String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }
    
/**
 * Method that returns Artist's placeOfBirth.
 * @return placeOfBirth
 */ 
    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

/**
 * Method for setting Artist's songs.
 * @param songs
 */ 
    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }
    
/**
 * Method that returns Artist's songs.
 * @return songs
 */     
    public ArrayList<Song> getSongs() {
        return songs;
    }
 
/**
 * Method for adding songs to listOfSongs.
 * @param songs
 */ 
    public void addSong (Song song) {
        ArrayList<Song> listOfSongs = this.getSongs();
        listOfSongs.add(song);
    } 
}