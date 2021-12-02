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
    
    
    App() {
        JFrame frame = new JFrame("Wiktoria Szitenhelm: Assignment 2");
        JPanel panel = new JPanel();
        panel.setBackground(Color.darkGray);
        JButton addManually = new JButton("Add Data Manually");
        addManually.addActionListener(new AddManuallyListener());
       
        /*addManually.addActionListener(e ->  {
            System.out.println( "Hello World!" );
            System.out.println( "boon" );
        }); */
              
        /*addManually.addActionListener(e ->  {
            Utils toCreate = new Utils();
            toCreate.createExampleArtists(JList<Artist> jList);
            scrollerA.setModel(new DefaultListModel());
            ((DefaultListModel)scrollerA.getModel()).addElement(jList);
            System.out.println( "Hello World!" );
            System.out.println( "boon" );
        });*/
        
        
        /*Utils toCreate = new Utils();
        Connection conn = null;
        conn = toCreate.connectToDatabase();*/
        
        
        JButton addFromDB = new JButton("Add Data Fron Database");
        JButton deleteS = new JButton("Delete Selected");
        panel.add(addManually);
        panel.add(addFromDB);
        panel.add(deleteS);
    
        JList jList = new JList();
        JScrollPane scrollerA = new JScrollPane(jList);
        scrollerA.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);    
        JMenuBar menuBar = new JMenuBar();
        JMenuItem menu = new JMenuItem("About");
        menu.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Assignment", "pop up info", JOptionPane.INFORMATION_MESSAGE));
        menuBar.add(menu);
        GridBagLayout layoutEast = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        JPanel panelEast = new JPanel();
        panelEast.setLayout(layoutEast);
        JLabel labelDob = new JLabel("Date of Birth:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        panelEast.add(labelDob, c);
        JTextField fieldDob = new JTextField(" ");
        c.fill = GridBagConstraints.HORIZONTAL;
        fieldDob.setPreferredSize( new Dimension( 100, 24 ) );
        c.gridx = 1;
        c.gridy = 0;
        panelEast.add(fieldDob, c);
        JLabel labelPob = new JLabel("Place of Birth:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        panelEast.add(labelPob, c);
        JTextField fieldPob = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        panelEast.add(fieldPob, c);  
        JLabel labelBow = new JLabel("Born on Weekend:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        panelEast.add(labelBow, c);
        JTextField fieldBow = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        panelEast.add(fieldBow, c);
        // ?
        JTextArea textArea = new JTextArea();
        JScrollPane scrollerS = new JScrollPane(textArea);
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
          
    /*class AddManuallyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Utils toCreate = new Utils();
            toCreate.createExampleArtists(JList<Artist> jList);
            scrollerA.setModel(new DefaultListModel());
            ((DefaultListModel)scrollerA.getModel()).addElement(jList);
            System.out.println( "Hello World!" );
            System.out.println( "boon" );
            System.out.println( "worked!!!" );
            }
    }*/
   
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        new App();
        
    }
}

