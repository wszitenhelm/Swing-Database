package CS2020.assignment2;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import CS2020.assignment2.Utils;
import CS2020.assignment2.Artist;
import CS2020.assignment2.Song;
import java.util.ArrayList;
import java.sql.*;


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
        
        /*Utils toCreate = new Utils();
        Connection conn = null;
        conn = toCreate.connectToDatabase();*/
              
        addFromDB = new JButton("Add Data Fron Database");
        addFromDB.addActionListener(new AddFromDB());
        deleteS = new JButton("Delete Selected");
        panel.add(addManually);
        panel.add(addFromDB);
        panel.add(deleteS);
    
        jList = new JList();
        jList.setModel(new DefaultListModel());
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
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        panelEast.add(fieldBow, c);
        // ?
        textArea = new JTextArea();
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
    }
          
    class AddManuallyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Utils toCreate = new Utils();
            toCreate.createExampleArtists(jList);
            addManually.setEnabled(true);
            System.out.println( "worked!!!" );
            }
    }
    
    class AddFromDB implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Utils toCreate = new Utils();
            toCreate.readArtistAndSongsFromDatabase(jList);
            System.out.println( "worked!!!" );
            }
    }
    
   
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        new App();
        
    }
}

