package clock;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import javax.swing.*;
import java.util.Observer;
import java.util.Observable;
import java.util.Set;

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
        
   



        menu = new JMenu("Alarm");

        menuBar.add(menu);

        JMenuItem addalarmitem = new JMenuItem("Set Alarm");

        menu.add(addalarmitem);
        
          final JFrame addalarm = new JFrame();
          addalarm.setLayout(null);
         addalarm.setSize(400, 400);
       
         JLabel datelabel =new JLabel("Date");  
        datelabel.setBounds(80,10,50,50);
        JLabel dateformat =new JLabel("(yyyymmdd)");  
        dateformat.setBounds(60,50,100,50);
        
        JLabel timelabel =new JLabel("Time");  
        timelabel.setBounds(280,10,50,50);
        JLabel timeformat =new JLabel("(hh:mm:ss)");  
        timeformat.setBounds(270,50,100,50);

       final JTextField Date = new JTextField("",20);
       Date.setBounds(50,100,100,30);
       
       final JTextField Time = new JTextField("",20);
       Time.setBounds(250,100,100,30);
       
       
       
         addalarm.add(datelabel);
         addalarm.add(dateformat);
         addalarm.add(Date);
         
          addalarm.add(timelabel);
          addalarm.add(timeformat);
          addalarm.add(Time);
       
       JButton Addalarmbutton = new JButton("Add");
        Addalarmbutton.setBounds(100,300,100,50);
          addalarm.add(Addalarmbutton);
           JButton Clear = new JButton("Reset");
        Clear.setBounds(200,300,100,50);
          addalarm.add(Clear);
           
          
         addalarmitem.addActionListener(new ActionListener() {
           
            @Override
            public void actionPerformed(ActionEvent e) {
 
            addalarm.setVisible(true);
            }

        });
         
         
        
        
         JMenuItem deletealarmitem = new JMenuItem("Delete Alarm");

        menu.add(deletealarmitem);

         JMenu menu1 = new JMenu("View");

        menuBar.add(menu1);
            
        JMenuItem ViewalarmItem = new JMenuItem("View Alarms");

        menu1.add(ViewalarmItem);
         final JFrame viewalarm = new JFrame();
         viewalarm.setTitle("Add Alarm");
           viewalarm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         viewalarm.setSize(400, 400); 
       
         ViewalarmItem.addActionListener(new ActionListener() {
           
            @Override
            public void actionPerformed(ActionEvent e) {
               
                
       
            viewalarm.setVisible(true);
            }

           
      

        });
         
              exitMenuItem.addActionListener(new ActionListener() {
           
            @Override
            public void actionPerformed(ActionEvent e) {
               
                
       
            System.exit(0);
            }

           
      

        });
              
              
              Addalarmbutton.addActionListener(new ActionListener()
{
  @Override
  public void actionPerformed(ActionEvent e)
  {
    // display/center the jdialog when the button is pressed
      
      String InputDate = Date.getText();
       String InputTime = Time.getText();
       DateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
       DateFormat timeformat = new SimpleDateFormat("HH:mm:ss");
        dateformat.setLenient(false);
        timeformat.setLenient(false);
          
        
        try {
            dateformat.parse(InputDate);
            LocalTime.parse(InputTime);
            
                String regx = ":";
            char[] ca = regx.toCharArray();
            for (char c : ca) {
               InputTime = InputTime.replace(""+c, "");
             }
         
            
             
               String ConcatAlarm = InputDate+InputTime;
               long result = Long.parseLong(ConcatAlarm);
               
               
                JTextField DialogMessage = new JTextField(ConcatAlarm);
               
               DialogMessage.setBounds(150,200,100,50);
               
             
              addalarm.add(DialogMessage);
              // addalarm.add(DialogMessageTime);
              // DialogMessage.setVisible(true);
              //DialogMessageTime.setVisible(true);
             
            
        } catch (ParseException | DateTimeParseException eg) {
            
            
    
            
           JOptionPane.showMessageDialog(addalarm, "Wrong Format (yyyyMMdd)", "Date", JOptionPane.ERROR_MESSAGE);
           JOptionPane.showMessageDialog(addalarm, "Wrong Format (HH:mm:ss)", "Time", JOptionPane.ERROR_MESSAGE);
             
        }
       
      
  }
});

        Clear.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e){
        Date.setText("");
        Time.setText("");
        addalarm.dispose();
        addalarm.setVisible(true);
     
       // ErrorMessageDate.setText("");
        //textfield.setText(null); //or use this
    }
});
              
 
        
        frame.pack();
        frame.setVisible(true);
        
        
        
        
        
        
        
    }
    
    
    public void update(Observable o, Object arg) {
        panel.repaint();
    }
    
    
}
