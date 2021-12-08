package CS2020.assignment2;
import java.util.HashMap; 
import java.time.LocalDate;
import java.time.format.*;
import java.time.temporal.ChronoField;
import CS2020.assignment2.Artist;
import CS2020.assignment2.Song;
import javax.swing.JList;
import java.util.ArrayList;
import java.util.UUID;
import javax.swing.DefaultListModel;
import java.sql.*;
import java.util.function.Consumer;
import java.util.Collections;

/**
 * Class generally for operating with Artists and Songs 
 * in the aspect of creating example artists, 
 * reading database to access artists 
 * and getting details about created/read from database artists.
 */
class Utils {

    /**
     * Method for returning songs' durations and titles
     * as HashMap named songs which is created inside this method. 
     * Method created with lambda expression. 
     * @param listOfSongs
     * @return songs
     */
    public static HashMap<UUID, String> returnSongDurationAndTitleFormatted(ArrayList<Song> listOfSongs) {
        HashMap<UUID, String> songs = new HashMap<>();        
        listOfSongs.forEach( (s) -> {UUID songID = s.getSongID();                    
                String songTitle = s.getTitle();
                int songDuration = s.getDuration();
                int seconds = (songDuration % 60);
                int minutes = ((songDuration - seconds) / 60 );
                String secondsS =Integer.toString(seconds);
                String minutesS =Integer.toString(minutes);
                String songValue = songTitle + "(" + minutesS + ":" + secondsS + ")";
                songs.put(songID, songValue);
                 });
         return songs;     
    }

    /**
     * Method for checking if Artist was born on weekend.
     * It uses formatter to format date, gets day as day of week
     * and then checks if that day was weekend.
     * @param dob
     * return true/false
     */
    public static boolean checkIfBornOnWeekend(String dob){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        LocalDate day = LocalDate.parse(dob, formatter); 
        String weekday = day.getDayOfWeek().name();
        if (weekday.equals("SATURDAY") || weekday.equals("SUNDAY")){
            return true;
        }
        else {
            return false; 
        }
    }
 
    /**
     * Method for creating example artists 
     * with all the needed fields. Method also creates songs 
     * and adds them as artists' fields. 
     * @param ist
     */
    public static void createExampleArtists(JList<Artist> list) {
        Artist artist1 = new Artist();
        artist1.setLastName("Blunt*");
        artist1.setFirstName("James");
        artist1.setDob("22 Feb 1974");
        artist1.setPlaceOfBirth("Tidworth");
        Song song1 = new Song();
        song1.setTitle("You're Beautiful");
        song1.setDuration(200);
        song1.setSongID();
        Song song2 = new Song();
        song2.setTitle("Cold");
        song2.setDuration(240);
        song2.setSongID();
        artist1.addSong(song1);
        artist1.addSong(song2);
        ((ModelWithSorting)list.getModel()).addElement(artist1);        
        Artist artist2 = new Artist();
        artist2.setLastName("Perry*");
        artist2.setFirstName("Katy");
        artist2.setDob("25 Oct 1984");
        artist2.setPlaceOfBirth("Santa Barbara");
        Song song3 = new Song();
        song3.setTitle("Roar");
        song3.setDuration(182);
        song3.setSongID();
        Song song4 = new Song();
        song4.setTitle("Teenage Dream");
        song4.setDuration(199);
        song4.setSongID();
        artist2.addSong(song3);
        artist2.addSong(song4);
        ((ModelWithSorting)list.getModel()).addElement(artist2);
    }
    
    /**
     * Method for creating connection to database. 
     * @return conn
     */ 
    public static Connection connectToDatabase() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:/home/codio/workspace/CS2020assignment2/resources/CS2020-assignment2.db");
            System.out.println("Connection to SQlite has been established");
        }
        catch(SQLException e)
        {
          System.err.println(e.getMessage());
        }
        return conn;
     }   

    /**
     * Method for getting all the details about artists from the list.
     * Creates ArrayList<String> called result that contains 
     * all the artists and their fields. 
     * @param list
     * return result
     */
    public static ArrayList<String> getDetails(JList<Artist> list){
        ArrayList<String> result = new ArrayList<String>();
        ModelWithSorting<Artist> artists = ((ModelWithSorting)list.getModel());
        int numberOfA = artists.getSize();
        int numberOfSongs = 0;    
        ArrayList<String> allsongs = new ArrayList<String>();
        for (int i=0; i<numberOfA; i++) {
            ArrayList<Song> songs = artists.getElementAt(i).getSongs();
            int numberofS = songs.size();
            numberOfSongs += numberofS;
            for (int y =0; y<numberofS; y++) {
                String song = songs.get(y).getTitle();
                allsongs.add(song);
            }
        }
        allsongs.sort((s1, s2) -> Math.abs(s1.length() - "a".length()) - Math.abs(s2.length() - "a".length()));
        System.out.println(allsongs);
        ArrayList<String> shortestSongs = new ArrayList<String>();
        ArrayList<String> longestSongs = new ArrayList<String>();
        int i = 1;
        shortestSongs.add(allsongs.get(0));
        longestSongs.add(allsongs.get(allsongs.size()-1));
        while (i < numberOfSongs) {
            if (allsongs.get(i).length() == allsongs.get(0).length()) {
                shortestSongs.add(allsongs.get(i));
                i += 1;
            }
            else {
                break;
            }
        }        
        int y = (allsongs.size() - 2);
        while (i < numberOfSongs) {
            if (allsongs.get(allsongs.size()-1).length() == allsongs.get(y).length()) {
                longestSongs.add(allsongs.get(y));
                y -= 1;
            }
            else {
                break;
            }
        }        
        System.out.println(shortestSongs);
        System.out.println(longestSongs);        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        HashMap<LocalDate, String> datesAndArtists = new HashMap<>();
        ArrayList<LocalDate> allDates = new ArrayList<LocalDate>();
        for (int x=0; x<numberOfA; x++) {
            String dateOfB = artists.getElementAt(x).getDob();
            String FnameOfA = artists.getElementAt(x).getFirstName();
            String LnameOfA = artists.getElementAt(x).getLastName();
            String name = FnameOfA + " " + LnameOfA;
            LocalDate artistDob = LocalDate.parse(dateOfB, formatter);
            allDates.add(artistDob);
            datesAndArtists.put(artistDob, name);
        }
        Collections.sort(allDates);        
        LocalDate oldest = allDates.get(0);
        LocalDate youngest = allDates.get(allDates.size()-1);
        System.out.println(youngest); 
        System.out.println(oldest);
        System.out.println(allDates);
        String youngestArtist = datesAndArtists.get(youngest);
        String oldestArtist = datesAndArtists.get(oldest);
        System.out.println(youngestArtist);                
        Object[] logestSongArr = longestSongs.toArray(); 
        Object[] shortestSongArr = shortestSongs.toArray();         
        String longestSongString = "[" + String.join(", ", longestSongs) + "]";
        String shortestSongString = "[" + String.join(", ", shortestSongs) + "]";
        result.add(String.valueOf(numberOfA));
        result.add(String.valueOf(numberOfSongs));
        result.add(oldestArtist);
        result.add(youngestArtist); 
        result.add(shortestSongString);
        result.add(longestSongString);
    return result;     
    }

    /**
     * Method for reading database to get artists and their
     * songs in order to add them to the list. 
     * Method works by executing two queries. 
     * @param list
     */
    public static void readArtistAndSongsFromDatabase(JList<Artist> list) {
        Connection con=null;
        PreparedStatement p1=null;
        PreparedStatement p2=null;
        ResultSet rs1=null;
        ResultSet rs2=null;
        con = connectToDatabase();
        try {
            String sql1 ="SELECT * FROM Artist";
            p1 = con.prepareStatement(sql1);
            rs1 = p1.executeQuery();
            while (rs1.next()) {
                String artistId1 = rs1.getString("artistID").trim();
                String wholeName = rs1.getString("name");
                String pob = rs1.getString("placeOfBirth");
                String dob = rs1.getString("dob");
                String[] splittedName = wholeName.split(" ");
                String name = splittedName[0];
                int length = splittedName.length;
                String surname = splittedName[length-1];
                if (length == 1) {
                    surname = "";
                }
                Artist artist = new Artist();
                UUID uuidA = UUID.fromString(artistId1);
                artist.setArtistID(uuidA);
                artist.setFirstName(name);
                artist.setLastName(surname);
                artist.setPlaceOfBirth(pob);
                artist.setDob(dob);                
                ((ModelWithSorting)list.getModel()).addElement(artist);
                String sql2 = "SELECT * FROM Song WHERE artistID = ?";
                p2 = con.prepareStatement(sql2);
                p2.setString(1, artistId1);
                rs2 = p2.executeQuery();
                while (rs2.next()) {
                    String title = rs2.getString("title");
                    int duration = rs2.getInt("duration");
                    title = title.trim();
                    Song song = new Song();                    
                    song.setSongID();
                    song.setTitle(title);
                    song.setDuration(duration);
                    artist.addSong(song);
                }
            }
        con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        }
}
    
