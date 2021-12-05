package CS2020.assignment2;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.*;
import javax.swing.JList;
import CS2020.assignment2.Artist;
import CS2020.assignment2.Song;
import CS2020.assignment2.ModelWithSorting;

public class Export {
    
    public FileWriter writer;
    public FileWriter writerSongs;
    
    public Export() {
        System.out.println("works till here");
    }
    
    public static void exportCSV(JList<Artist> jList) {
        System.out.println("got here");
        
        ModelWithSorting<Artist> artists = ((ModelWithSorting)jList.getModel());
        int numberOfA = artists.getSize();
        
        List<String[]> listA = new ArrayList<>();
        String[] headerA = {"artistID", "dob", "placeOfBirth"};
        listA.add(headerA);
        
        List<String[]> listS = new ArrayList<>();
        String[] headerS = {"songID", "artistID", "title", "duration"};
        listS.add(headerS);        
        
        for (int i=0; i<numberOfA; i++){
            UUID artistId = artists.getElementAt(i).getArtistID();
            String artistID = artistId.toString();
            String[] artistElements = {artistID, artists.getElementAt(i).getDob(), artists.getElementAt(i).getPlaceOfBirth()};
            listA.add(artistElements);
            ArrayList<Song> songs = artists.getElementAt(i).getSongs();
            int numberofS = songs.size();
  
            for (int k=0; k<numberofS; k++){
                UUID songId = songs.get(k).getSongID();
                String songID = songId.toString();
                String title = songs.get(k).getTitle();
                int duration = songs.get(k).getDuration();
                String durationS = String.valueOf(duration);
                String[] songElements = {songID, artistID, title, durationS};
                listS.add(songElements);
            }
            
        }
        
        
    	//FileWriter writer = null;
    	try {
            //FileWriter writer;
            CSVWriter writer = new CSVWriter(new FileWriter("/home/codio/workspace/CS2020assignment2/resources/artists.csv"));
            CSVWriter writerSongs = new CSVWriter(new FileWriter("/home/codio/workspace/CS2020assignment2/resources/songs.csv"));
            writer.writeAll(listA);
            System.out.println("Write to CSV file Succeeded!!!");
            writer.close();
            writerSongs.writeAll(listS);
            writerSongs.close();
        }
    	catch(Exception ee)
    	{
    		ee.printStackTrace();
    	}
    	//finally
    	/*{
    		try
    		{
    			writer.close();
    		}
    		catch(IOException ie)
    		{
    			System.out.println("Error occured while closing the fileWriter");
    			ie.printStackTrace();
    		}
    	}*/
    }
}
