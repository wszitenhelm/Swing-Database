import java.util.HashMap; // import the HashMap class
import java.time.LocalDate;

class Utils {
    
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
    
    
}