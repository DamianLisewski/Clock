package clock;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.Observer;
import java.util.Observable;

public class View implements Observer {
    
    ClockPanel panel;
    
    public View(Model model) {
        JFrame frame = new JFrame();
        panel = new ClockPanel(model);
        //frame.setContentPane(panel);
        frame.setTitle("Java Clock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Start of border layout code
        
        // I've just put a single button in each of the border positions:
        // PAGE_START (i.e. top), PAGE_END (bottom), LINE_START (left) and
        // LINE_END (right). You can omit any of these, or replace the button
        // with something else like a label or a menu bar. Or maybe you can
        // figure out how to pack more than one thing into one of those
        // positions. This is the very simplest border layout possible, just
        // to help you get started.
        
        Container pane = frame.getContentPane();
        

        panel.setPreferredSize(new Dimension(400, 400));
        pane.add(panel, BorderLayout.CENTER);
         
        JMenuBar menuBar = new JMenuBar();

        frame.setJMenuBar(menuBar);

        
        JMenu menu = new JMenu("File");

        menuBar.add(menu);

        JMenuItem exitMenuItem = new JMenuItem("Exit");

        menu.add(exitMenuItem);

        menu = new JMenu("Set");

        menuBar.add(menu);

        JMenuItem timeMenuItem = new JMenuItem("Set Alarm");

        menu.add(timeMenuItem);

         JMenu menu1 = new JMenu("View");

        menuBar.add(menu1);

        JMenuItem ViewalarmItem = new JMenuItem("View Alarms");

        menu1.add(ViewalarmItem);
         final JFrame viewalarm = new JFrame();
         viewalarm.setSize(400, 400); 
       
         ViewalarmItem.addActionListener(new ActionListener() {
           
            @Override
            public void actionPerformed(ActionEvent e) {
               
                
       
            viewalarm.setVisible(true);
            }

           
      

        });

        
        // End of borderlayout code
        
        frame.pack();
        frame.setVisible(true);
    }
    
    public void update(Observable o, Object arg) {
        panel.repaint();
    }
}
