package util;

public class Artist {
    private UUID artistID;
    private String firstName;
    private String lastName;
    private String dob;
    private String placeOfBirth;
    private ArrayList<Song> songs;
    
    Artist () {
        this.artistID = UUID.randomUUID();
    }
    
    // is there any point in this method? 
    public void setArtistID (artistID) {
        this.artistID = artistID;
    }
    public UUID getArtistID {
        return artistID;
    }
    
    public void setFirstName (firstName) {
        this.firstName = firstName;
    }
    public String getFirstName {
        return firstName;
    }
    
    public void setLastName (lastName) {
        this.lastName = lastName;
    }
    public String getLastName {
        return lastName;
    }
    
    public void setDob (dob) {
        this.dob = dob;
    }
    public String getDob {
        return dob;
    }
    
    public void setPlaceOfBirth (placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }
    public String getPlaceOfBirth {
        return placeOfBirth;
    }
    
    // is there any point in this method?
    public void setSongs(songs) {
        this.songs = songs;
    }
    public ArrayList<Song> getSongs {
        return songs;
    }
    
    public addSong(Song song) {
        this.songs.add(song)
    }
    
}