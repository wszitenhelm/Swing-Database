package CS2020.assignment2;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import CS2020.assignment2.Utils;
import CS2020.assignment2.Artist;
import CS2020.assignment2.Song;
import CS2020.assignment2.ModelWithSorting;
import CS2020.assignment2.Export;
import java.util.Arrays;
import java.util.ArrayList;
import java.sql.*;
import java.util.HashMap;
import java.util.UUID;

/**
 * Class for running the whole Swing Music application.
 * Firsly class creates JPanel with all the JComponents 
 * that have ActionListeners to make app work. 
 */
public class App
{   
    public JPanel panel;   
    public JFrame frame;
    public JButton addManually;
    public JButton addFromDB;
    public JButton deleteS;
    public JList<Artist> jList;
    public JScrollPane scrollerA;
    public JMenuBar menuBar;
    public JButton menu;    
    public JButton dataMenu;
    public JButton exportMenu;
    public GridBagLayout layoutEast;
    public GridBagConstraints c;
    public JPanel panelEast;
    public JLabel labelDob;
    public JTextField fieldDob;
    public JLabel labelPob;
    public JTextField fieldPob;
    public JLabel labelBow;
    public JTextField fieldBow;
    public JTextArea textArea;
    public JScrollPane scrollerS;
    public JPopupMenu popupmenu;

    /**
     * Class constructor that creates JFrame 
     * and other components to make
     * an user friendly GUI.
     */
    App() {
        frame = new JFrame("Wiktoria Szitenhelm: Assignment 2");
        panel = new JPanel();
        panel.setBackground(Color.darkGray);
        addManually = new JButton("Add Data Manually");
        addManually.addActionListener(new AddManuallyListener());     
        addFromDB = new JButton("Add Data From Database");
        addFromDB.addActionListener(new AddFromDB());
        deleteS = new JButton("Delete Selected");
        deleteS.setEnabled(false);
        panel.add(addManually);
        panel.add(addFromDB);
        panel.add(deleteS);   
        jList = new JList();
        jList.setModel(new ModelWithSorting());           
        scrollerA = new JScrollPane(jList);
        scrollerA.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);    
        menuBar = new JMenuBar();
        menu = new JButton("About");
        menu.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Assignment 2 App v.0.1", "About", JOptionPane.INFORMATION_MESSAGE));
        dataMenu = new JButton("Data");               
        dataMenu.addActionListener(new DataListener());        
        exportMenu = new JButton("Export to CSV");       
        exportMenu.addActionListener(new ExportData());        
        menuBar.add(menu);
        menuBar.add(dataMenu);
        menuBar.add(exportMenu);
        layoutEast = new GridBagLayout();
        c = new GridBagConstraints();
        panelEast = new JPanel();
        panelEast.setLayout(layoutEast);
        labelDob = new JLabel("Date of Birth:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        panelEast.add(labelDob, c);
        fieldDob = new JTextField(" ");
        fieldDob.setEditable(false);
        c.fill = GridBagConstraints.HORIZONTAL;
        fieldDob.setPreferredSize( new Dimension( 100, 24 ) );
        c.gridx = 1;
        c.gridy = 0;
        panelEast.add(fieldDob, c);
        labelPob = new JLabel("Place of Birth:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        panelEast.add(labelPob, c);
        fieldPob = new JTextField();
        fieldPob.setEditable(false);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        panelEast.add(fieldPob, c);  
        labelBow = new JLabel("Born on Weekend:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        panelEast.add(labelBow, c);
        fieldBow = new JTextField();
        fieldBow.setEditable(false);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        panelEast.add(fieldBow, c);
        textArea = new JTextArea();
        textArea.setEditable(false);
        scrollerS = new JScrollPane(textArea);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.ipady = 400;
        c.ipadx = 200;
        c.gridx = 0;
        c.gridy = 3;
        panelEast.add(scrollerS, c);
        scrollerS.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); 
        frame.getContentPane().add(BorderLayout.NORTH, menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, scrollerA);
        frame.getContentPane().add(BorderLayout.EAST, panelEast);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.setSize(800, 600);
        frame.setVisible(true);        
        frame.addMouseListener(new RightMouseListener());
        textArea.addMouseListener(new RightMouseListener());
        jList.addMouseListener(new RightMouseListener());        
        ListSelectionListener listSelectionListener = new ListSelectionListener() {
          public void valueChanged(ListSelectionEvent listSelectionEvent) {
            boolean adjust = listSelectionEvent.getValueIsAdjusting();
            if (!adjust) {
              JList list = (JList) listSelectionEvent.getSource();
              int selections[] = list.getSelectedIndices();
            if (selections.length > 0) {
              deleteS.setEnabled(true);      
              Object selectionValues[] = list.getSelectedValues();
              String dob = ((Artist)selectionValues[0]).getDob();
              fieldDob.setText(dob);
              String pob = ((Artist)selectionValues[0]).getPlaceOfBirth();
              fieldPob.setText(pob);
              boolean result = Utils.checkIfBornOnWeekend(dob);
              if (result == true) {
                  fieldBow.setText("yes");
              }
              else {
                  fieldBow.setText("no");
              }                              
              ArrayList<Song> listOfSongs = ((Artist)selectionValues[0]).getSongs();
              HashMap<UUID, String> songs = Utils.returnSongDurationAndTitleFormatted(listOfSongs);
              textArea.setText("");
              int a = 1;
              for (String song: songs.values()) {
                  textArea.append(a + ". " + song+ "\n");
                  a += 1;
              }              
             }            
            }
          }
          };
        jList.addListSelectionListener(listSelectionListener);

        /**
         * Class for deleting artist from the scrollable list.
         * This class implements ActionListener. 
         */
        class DeleteArtistListener implements ActionListener {
                @Override 
                /**
                 * Method for perfoming action when event happens. 
                 * This method remove artist from the list 
                 * and set empty text for text fields. 
                 * @param e
                 */
                public void actionPerformed(ActionEvent e) {
                    int decision = JOptionPane.showConfirmDialog(frame, "Are you sure?");
                    if (decision == JOptionPane.YES_OPTION){
                        int selected = jList.getSelectedIndex();                        
                        ((ModelWithSorting)jList.getModel()).remove(selected);                        
                        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        jList.setSelectedIndex(0);
                    }
                    else {
                        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    }
                    if (jList.getModel().getSize() == 0) {
                        deleteS.setEnabled(false);
                        fieldBow.setText("");
                        fieldPob.setText("");
                        fieldDob.setText("");
                        textArea.setText("");
                    }
                }                
              }
              deleteS.addActionListener(new DeleteArtistListener()); 
    }
    
    /**
     * Class for listening for right mouse clicking.
     * This class extends MouseAdapter.
     */
    class RightMouseListener extends MouseAdapter {
            /**
             * Method for perfoming action when event happens.
             * This method overrides method mouseClicked(). 
             * It lets by adding ActionListener to the created button,
             * delete the artist if it was a right mouse click and if artist is selected.
             * @param e
             */
           @Override
           public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                   int x = e.getX();
                   int y = e.getY(); 
                   JButton button = new JButton("Delete selected artist");
                   popupmenu = new JPopupMenu();
                   popupmenu.add(button); 
                   popupmenu.setVisible(true);
                   popupmenu.show(frame, x, y); 
                   int selected = jList.getSelectedIndex();
                   if (selected == -1){
                        button.setEnabled(false); 
                   }
                   else{
                     button.addActionListener(new DeleteArtistListenerClick());
                   }
                }
           }
    }
           
   /**
    * Class for showing current data information 
    * after clicking button "Data".
    * Class implements ActionListener.
    */
    class DataListener implements ActionListener {
        /**
         * Method for performing action when event happens.
         * This method overrides method actionPerformed().
         * Method gets details from jList and then display them.
         * @param e
         */
        @Override 
        public void actionPerformed(ActionEvent e) {
            if (jList.getModel().getSize() != 0) {          
            ArrayList<String> currentData = Utils.getDetails(jList); 
            Utils.getDetails(jList);  
            String message = "Number of artists:" + currentData.get(0) + "\n" 
                +"Number of songs: " + currentData.get(1) + "\n" + "Oldest artist: " + currentData.get(2) + "\n"
                +"Youngest artist: " + currentData.get(3) + "\n" + "Shortest song names are: " + currentData.get(4) + "\n"
                +"Longest song names are: " + currentData.get(5) + "\n";
            JOptionPane.showMessageDialog(frame, message, "Data Statistics", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                    JOptionPane.showMessageDialog(frame, "The list is empty", "Data Statistics", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    /**
     * Class for calling method that is responsible 
     * for exporting data and creating csv files. 
     */
    class ExportData implements ActionListener {
        /**
         * Method for performing action when event happens.
         * Overrides method actionPerformed().
         * Creates new Export object and calls method 
         * to export data and create csv fils.
         * @param e
         */
        @Override 
        public void actionPerformed(ActionEvent e) {
            System.out.println("export works");
            Export export = new Export();
            export.exportCSV(jList);
        }
    }

    /**
     * Class for letting user delete the artist by right mouse clicking.
     * This class implements ActionListener.
     */
    class DeleteArtistListenerClick implements ActionListener {
                /** 
                 * Method for perfoming action when event happens.
                 * This method overrides method actionPerformed().
                 * It removes selected artist and set empty text fields.
                 * @param e 
                 */
                @Override 
                public void actionPerformed(ActionEvent e) {
                    int selected = jList.getSelectedIndex();
                    ((ModelWithSorting)jList.getModel()).remove(selected);
                    jList.clearSelection();
                    fieldBow.setText("");
                    fieldPob.setText("");
                    fieldDob.setText("");
                    textArea.setText("");
                    popupmenu.setVisible(false);
                    deleteS.setEnabled(false);
                }
     }
     
    /**
     * Class that implements ActionListener 
     * to add manually created artists 
     * to the scrollable JList.
     */
    class AddManuallyListener implements ActionListener {
        /**
         * Method that performs action when event happens.
         * Overrides method actionPerformed.
         * Creates new Utils object and calls method
         * responsible for creating example artists.
         * @param e 
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            Utils toCreate = new Utils();
            toCreate.createExampleArtists(jList);            
            ((ModelWithSorting)jList.getModel()).sort();
            addManually.setEnabled(false);
            }
    }
     
    /**
     * Class for adding artists from database.
     * This class implements ActionListener.
     */
    class AddFromDB implements ActionListener {
        /**
         * Method for performing action when event happens.
         * Overrides method actionPerformed().
         * Creates new Utils object and calls method
         * responsible for reading artists from database.
         * @param e 
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            Utils toCreate = new Utils();
            toCreate.readArtistAndSongsFromDatabase(jList);   
            ((ModelWithSorting)jList.getModel()).sort();
            addFromDB.setEnabled(false);
            }
    }
   
    /**
     * Main method that starts the whole application
     */
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        new App();       
    }
}

