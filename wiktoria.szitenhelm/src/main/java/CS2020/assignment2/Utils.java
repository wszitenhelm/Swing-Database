package CS2020.assignment2;
import java.util.HashMap; 
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import CS2020.assignment2.Artist;
import CS2020.assignment2.Song;
import javax.swing.JList;
import java.util.ArrayList;
import java.util.UUID;
import javax.swing.DefaultListModel;
import java.sql.*;


class Utils {
    
    // implement here this method using lambda expression. ! 
    public static HashMap<UUID, String> returnSongDurationAndTitleFormatted(ArrayList<Song> listOfSongs) {
        HashMap<UUID, String> songs = new HashMap<>();
        int aLength = listOfSongs.size();
        if (aLength > 0) { 
            for (int i = 0; i < aLength; i++) {
                UUID songID = listOfSongs.get(i).getSongID();
                String songTitle = listOfSongs.get(i).getTitle();
                int songDuration = listOfSongs.get(i).getDuration();
                int seconds = (songDuration % 60);
                int minutes = ((songDuration - seconds) / 60 );
                String secondsS =Integer.toString(seconds);
                String minutesS =Integer.toString(minutes);
                // System.out.printf("\"%s\", (%s), %d:%02d%n", title, artist, length / 60, length % 60);
                String songValue = songTitle + "(" + minutesS + ":" + secondsS + ")";
                songs.put(songID, songValue);
            }
        }
        return songs;
    }
    
    public static boolean checkIfBornOnWeekend(String dateToCheck) {
        LocalDate parsedDate = LocalDate.parse(dateToCheck);
        int dayNumber = parsedDate.get(ChronoField.DAY_OF_WEEK);
        if ( dayNumber == 6 || dayNumber == 7 ) {
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
        Song song2 = new Song();
        artist1.addSong(song1);
        artist1.addSong(song2);
        ((DefaultListModel)list.getModel()).addElement(artist1);
        Artist artist2 = new Artist();
        artist2.setLastName("Perry*");
        artist2.setFirstName("Katy");
        artist2.setDob("25 Oct 1984");
        artist2.setPlaceOfBirth("Santa Barbara");
        Song song3 = new Song();
        Song song4 = new Song();
        artist2.addSong(song3);
        artist2.addSong(song4);
        ((DefaultListModel)list.getModel()).addElement(artist2);
    }
    
    public static Connection connectToDatabase() {
        Connection conn = null;
        try {
            //String url = "jdbc:sqlite:/home/codio/workspace/CS2020assignment2/resources/CS2020assignment2.db";
            conn = DriverManager.getConnection("jdbc:sqlite:/home/codio/workspace/CS2020assignment2/resources/CS2020-assignment2.db");
            System.out.println("Connection to SQlite has been established");
        }
        // not return here?
        // return conn
        catch(SQLException e)
        {
          // if the error message is "out of memory",
          // it probably means no database file is found
          System.err.println(e.getMessage());
        }
        return conn;
        // WHAT WITH CONNECTION CLOSING?
        /*finally
        {
          try
          {
            if(conn != null)
              conn.close();
          }
          catch(SQLException e)
          {
            // connection close failed.
            System.err.println(e.getMessage());
          }
        }*/
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
                String artistId1 = rs1.getString("artistID");
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
                ((DefaultListModel)list.getModel()).addElement(artist);
                String sql2 = "SELECT * FROM Song";
                p2 = con.prepareStatement(sql2);
                rs2 = p2.executeQuery();
                while (rs2.next()) {
                    String artistId2 = rs2.getString("artistID");
                    if (artistId1 == artistId2) {
                        String songId = rs2.getString("songID");
                        String title = rs2.getString("title");
                        int duration = rs2.getInt("duration");
                        title = title.trim();
                        Song song = new Song();
                        UUID uuidS = UUID.fromString(songId);
                        song.setSongID(uuidS);
                        song.setTitle(title);
                        song.setDuration(duration);
                        artist.addSong(song);
                    }
                    else {
                        break;
                    }
                }
            }
        con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        }
}
    
