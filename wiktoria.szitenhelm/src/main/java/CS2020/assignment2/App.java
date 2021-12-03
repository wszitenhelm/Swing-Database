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
//import CS2020.assignment2.ModelWithSorting;
import java.util.Arrays;
import java.util.ArrayList;
import java.sql.*;
import java.util.HashMap;
import java.util.UUID;

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
    public JMenuItem menu;
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
    
    App() {
        frame = new JFrame("Wiktoria Szitenhelm: Assignment 2");
        panel = new JPanel();
        panel.setBackground(Color.darkGray);
        addManually = new JButton("Add Data Manually");
        addManually.addActionListener(new AddManuallyListener());     
        addFromDB = new JButton("Add Data Fron Database");
        addFromDB.addActionListener(new AddFromDB());
        deleteS = new JButton("Delete Selected");
        deleteS.setEnabled(false);
        panel.add(addManually);
        panel.add(addFromDB);
        panel.add(deleteS);   
        jList = new JList();
        //SortableListModel sortableListModel = new SortableListModel(listModel);
        //JList sortableList = new JList(sortableListModel);
        jList.setModel(new DefaultListModel());
        
        //jList.setModel(new ModelWithSorting());
        //ModelWithSorting implements ListModel
            
            
        scrollerA = new JScrollPane(jList);
        scrollerA.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);    
        menuBar = new JMenuBar();
        menu = new JMenuItem("About");
        menu.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Assignment", "pop up info", JOptionPane.INFORMATION_MESSAGE));
        menuBar.add(menu);
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
        // ?
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
              //static !!!! no need to create Utilis u = new Utilis();
              boolean result = Utils.checkIfBornOnWeekend(dob);
              if (result == true) {
                  fieldBow.setText("yes");
              }
              else {
                  fieldBow.setText("no");
              }
                 
             
              ArrayList<Song> listOfSongs = ((Artist)selectionValues[0]).getSongs();
              HashMap<UUID, String> songs = Utils.returnSongDurationAndTitleFormatted(listOfSongs);
              System.out.println(listOfSongs);
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
        
        class DeleteArtistListener implements ActionListener {
                @Override 
                public void actionPerformed(ActionEvent e) {
                    int decision = JOptionPane.showConfirmDialog(frame, "Are you sure?");
                    if (decision == JOptionPane.YES_OPTION){
                        int selected = jList.getSelectedIndex();
                        ((DefaultListModel)jList.getModel()).remove(selected);
                        System.out.println("deleted ");
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
                        System.out.println("sieze ok");
                    }
                }
                
              }
              deleteS.addActionListener(new DeleteArtistListener()); 
    }
          
    class AddManuallyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Utils toCreate = new Utils();
            toCreate.createExampleArtists(jList);
            
            //jList.getModel.sort();
            //sortJList(jList);
            
            addManually.setEnabled(false);
            System.out.println( "addActionListener worked!!!" );
            }
    }
    
    class AddFromDB implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Utils toCreate = new Utils();
            toCreate.readArtistAndSongsFromDatabase(jList);
            
            //jList.getModel.sort();
            //sortJList(jList);
            
            addFromDB.setEnabled(false);
            System.out.println( "worked!!!" );
            }
    }
    
    /*public JList sortJList(JList list) {
        ListModel model = list.getModel();
        String[] strings = new String[model.getSize()];
        for(int i=0;i<strings.length;i++){
            strings[i]=model.getElementAt(i).toString();
        }
        Arrays.sort(strings);
        list.setListData(strings);
        return list;
    }*/
    
    /*public void sortJList(JList list) {
        ListModel model = list.getModel();
        int n = model.getSize();
        String[] data = new String[n];
        
        for(int i=0;i<n;i++){
            data[i] = (String) model.getElementAt(i);
        }
        Arrays.sort(data);
        list.setListData(data);
    }*/
        
    
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        new App();
        
    }
}

