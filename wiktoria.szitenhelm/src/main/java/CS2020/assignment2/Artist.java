package CS2020.assignment2;
import java.util.ArrayList;
import java.util.UUID;

public class Artist {
    private UUID artistID;
    private String firstName;
    private String lastName;
    private String dob;
    private String placeOfBirth;
    private ArrayList<Song> songs;
    
    public String toString() { 
        return firstName + " " + lastName;
    }

        
    Artist () {
        this.artistID = UUID.randomUUID();
        this.songs = new ArrayList<Song>();
    }
    
    // is there any point in this method? 
    public void setArtistID (UUID artistID) {
        this.artistID = artistID;
    }
    public UUID getArtistID() {
        return artistID;
    }
    
    public void setFirstName (String firstName) {
        this.firstName = firstName;
    }
    public String getFirstName() {
        return firstName;
    }
    
    public void setLastName (String lastName) {
        this.lastName = lastName;
    }
    public String getLastName() {
        return lastName;
    }
    
    public void setDob (String dob) {
        this.dob = dob;
    }
    public String getDob() {
        return dob;
    }
    
    public void setPlaceOfBirth (String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }
    public String getPlaceOfBirth() {
        return placeOfBirth;
    }
    
    // is there any point in this method?
    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }
    public ArrayList<Song> getSongs() {
        return songs;
    }
    
    public void addSong (Song song) {
        ArrayList<Song> listOfSongs = this.getSongs();
        listOfSongs.add(song);
    }
    
}