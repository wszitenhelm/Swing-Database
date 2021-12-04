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

class Utils {
    

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

    
    public static boolean checkIfBornOnWeekend(String dob){
        //return true if artis was born on the weekend, false otherwise 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        LocalDate day = LocalDate.parse(dob, formatter); 
        //String weekday = day.getDayOfWeek().toString(); 
        String weekday = day.getDayOfWeek().name();
        //check if weekend
        if (weekday.equals("SATURDAY") || weekday.equals("SUNDAY")){
            return true;
        }
        else {
            return false; 
        }
    }
    
    public static void createExampleArtists(JList<Artist> list) {
        //list.setModel(new DefaultListModel());
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
        //((DefaultListModel)list.getModel()).addElement(artist1);
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
        //((DefaultListModel)list.getModel()).addElement(artist2);
        ((ModelWithSorting)list.getModel()).addElement(artist2);
    }
    
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
                Artist artist = new Artist();
                UUID uuidA = UUID.fromString(artistId1);
                artist.setArtistID(uuidA);
                artist.setFirstName(name);
                artist.setLastName(surname);
                artist.setPlaceOfBirth(pob);
                artist.setDob(dob);
                
                ((ModelWithSorting)list.getModel()).addElement(artist);
                //((DefaultListModel)list.getModel()).addElement(artist);
                String sql2 = "SELECT * FROM Song WHERE artistID = ?";
                p2 = con.prepareStatement(sql2);
                p2.setString(1, artistId1);
                rs2 = p2.executeQuery();
                while (rs2.next()) {
                        //String songId = rs2.getString("songID").trim();
                        String title = rs2.getString("title");
                        int duration = rs2.getInt("duration");
                        title = title.trim();
                        Song song = new Song();
                        //UUID uuidS = UUID.fromString(songId);
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
    
