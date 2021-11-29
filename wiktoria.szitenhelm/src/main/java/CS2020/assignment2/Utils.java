package util;
import java.util.HashMap; // import the HashMap class
import java.time.LocalDate;
import util.Artist;
import util.Song;
import javax.swing.JList;

class Utils {
    
    // implement here this function  using lambda expression. ! 
    public static HashMap<UUID, String> returnSongDurationAndTitleFormatted(ArrayList<Song> listOfSongs) {
        HashMap<UUID, String> songs = new HashMap<>();
        for (i = 0; i < listOfSongs.length; i++) {
            songID = listOfSongs[i].getSongID();
            songTitle = listOfSongs[i].getTitle();
            songDuration = listOfSongs[i].getDuration();
            int durationInt = Integer.parseInt(songDuration);
            int seconds = (durationInt % 60);
            int minutes = ((durationInt - seconds) / 60 );
            String secondsS =Integer.toString(seconds);
            String minutesS =Integer.toString(minutes);
            String songValue = songTitle + "(" + minutesS + ":" + secondsS + ")";
            songs.put(songID, songValue);
        }
        return songs;
    }
    
    
    
    public static checkIfBornOnWeekend(String dateToCheck) {
        LocalDate parsedDate = LocalDate.parse(dateToCheck);
        int dayNumber = LocalDate.get(ChronoField.DAY_OF_WEEK);
        if ( dayNumber == "6" || dayNumber == "7" ) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public static void createExampleArtists(JList<Artist> list) {
        list.setModel(new DefaultListModel());
        Artist artist1 = new Artist();
        artist1.setLastName("Blunt*");
        artist1.setFirstName("James");
        artist1.setDob("22 Feb 1974");
        artist1.setPlaceOfBirth("Tidworth");
        Song song1 = new Song();
        Song song2 = new Song();
        artist1.addSong(song1);
        artist1.addSong(song2);
        (DefaultListModel)list.getModel().addElement(artist1);
        Artist artist2 = new Artist();
        artist2.setLastName("Perry*");
        artist2.setFirstName("Katy");
        artist2.setDob("25 Oct 1984");
        artist2.setPlaceOfBirth("Santa Barbara");
        Song song3 = new Song();
        Song song4 = new Song();
        artist2.addSong(song3);
        artist2.addSong(song4);
        DefaultListModel model = (DefaultListModel)list.getModel().addElement(artist2);
    }
}
