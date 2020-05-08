package clock;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import javax.swing.*;
import java.util.Observer;
import java.util.Observable;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class View implements Observer {
    
    ClockPanel panel;
     ActionListener listener;
    javax.swing.Timer timer;
    
    
    public View(Model model) {
        final JFrame frame = new JFrame();
        panel = new ClockPanel(model);
        //frame.setContentPane(panel);
        frame.setTitle("Java Clock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         final PriorityQueue<Alarm> q;
          q = new SortedArrayPriorityQueue<>(5);
          
             int day=0;
        int month=0;
        int year=0;
         int hour = 0;
         int minute = 0;
         int second = 0;
        
        Calendar date = Calendar.getInstance();
        
        day = date.get(Calendar.DAY_OF_MONTH);
        month = date.get(Calendar.MONTH)+1;
        year = date.get(Calendar.YEAR);
         hour = date.get(Calendar.HOUR_OF_DAY);
        minute = date.get(Calendar.MINUTE);
        second = date.get(Calendar.SECOND);
        
        Date time = new Date();
        
       SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); //like "HH:mm" or just "mm", whatever you want
       
       
       String currenttime = sdf.format(time);
       
       
       Date current = new Date();
        
       SimpleDateFormat dat = new SimpleDateFormat("yyyyMMdd"); //like "HH:mm" or just "mm", whatever you want
       
       
       final String currentdate = dat.format(current);
           String regx = ":";
                char[] ca = regx.toCharArray();
                 for (char c : ca) {
                   currenttime = currenttime.replace(""+c, "");
             }
      
        String day1 = Integer.toString(day); 
        String month1 = Integer.toString(month); 
        String year1 = Integer.toString(year); 
      String hour1 = Integer.toString(hour); 
        String minute1 = Integer.toString(minute); 
        String second1 = Integer.toString(second);
       // System.out.println(currentdate);
       // System.out.println(currenttime);
        String Checkdate = currentdate+currenttime;
        final long now = Long.parseLong(Checkdate);
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
         
          JLabel namelabel =new JLabel("Alarm Name");  
        namelabel.setBounds(160,10,100,50);
       final JTextField alarmname = new JTextField("",20);
       alarmname.setBounds(145,60,100,30);
        String dash = "/";
        String dot = ":";
        String space = " ";
         JLabel currentdatelabel =new JLabel(day1+dash+month1+dash+year1+space+hour1+dot+minute1+dot+second1);  
        currentdatelabel.setBounds(150,250,120,50);
       
         JLabel datelabel =new JLabel("Date");  
        datelabel.setBounds(80,110,50,50);
        JLabel dateformat =new JLabel("(yyyymmdd)");  
        dateformat.setBounds(60,150,100,50);
        
        JLabel timelabel =new JLabel("Time");  
        timelabel.setBounds(280,110,50,50);
        JLabel timeformat =new JLabel("(hh:mm:ss)");  
        timeformat.setBounds(270,150,100,50);

       final JTextField Date = new JTextField("",20);
       Date.setBounds(50,200,100,30);
       
       final JTextField Time = new JTextField("",20);
       Time.setBounds(250,200,100,30);
       
         addalarm.add(namelabel);
         addalarm.add(alarmname);
         
       addalarm.add(currentdatelabel);
       
       
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
         viewalarm.setTitle("View Alarm");
           viewalarm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         viewalarm.setSize(400, 400); 
       
         ViewalarmItem.addActionListener(new ActionListener() {
           
            @Override
            public void actionPerformed(ActionEvent e) {
               
                
            System.out.println(q); 
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
           String InputAlarm = alarmname.getText();
            
            
                String regx = ":";
                char[] ca = regx.toCharArray();
                 for (char c : ca) {
                   InputTime = InputTime.replace(""+c, "");
             }
         
            
             
               String ConcatAlarm = InputDate+InputTime;
             
               
                
               
                long priority = Long.parseLong(ConcatAlarm.substring(ConcatAlarm.lastIndexOf(' ') + 1));
                
                 Alarm alarm = new Alarm(InputAlarm,priority);
                
             
                try {
                    
                    if(priority<=now){
                    
                    JOptionPane.showMessageDialog(addalarm, "Date needs to be set in the future : Set Date Time in futute", "Wrong Date", JOptionPane.ERROR_MESSAGE);
                    
                    }
                    else{
                    q.add(alarm, priority);
                     String MessageAdd = "Adding a Alarm called " + alarm.getName() + " with the Date" + InputDate + " and Time " +InputTime ;
                JOptionPane.showMessageDialog(addalarm, MessageAdd, "Added Alarm", JOptionPane.INFORMATION_MESSAGE);
                    }} catch (QueueOverflowException a) {
                    
                    
                     JOptionPane.showMessageDialog(addalarm, "Queue is Full : Remove Some Alarms!", "Queue Full", JOptionPane.INFORMATION_MESSAGE);
                    
               }
             
             //  JTextField DialogMessageName = new JTextField(InputAlarm);
               // JTextField DialogMessage = new JTextField(ConcatAlarm);
               
                 //  DialogMessageName.setBounds(150,150,100,50);
              // DialogMessage.setBounds(150,200,100,50);
            
               
              //addalarm.add(DialogMessageName);
             // addalarm.add(DialogMessage);
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
        alarmname.setText("");
        addalarm.dispose();
        addalarm.setVisible(true);
     
       // ErrorMessageDate.setText("");
        //textfield.setText(null); //or use this
    }
});
              
 
        
        frame.pack();
        frame.setVisible(true);
        
        
        
        class checkalarm extends TimerTask {
    @Override
    public void run() {
        try{
            long alarm = q.head().getAlarm();
                       
       
        
     
        
      
        
        Date time = new Date();
        
       SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); //like "HH:mm" or just "mm", whatever you want
       
       
       String currenttime = sdf.format(time);
       
       
       Date current = new Date();
        
       SimpleDateFormat dat = new SimpleDateFormat("yyyyMMdd"); //like "HH:mm" or just "mm", whatever you want
       
       
       final String currentdate = dat.format(current);
           String regx = ":";
                char[] ca = regx.toCharArray();
                 for (char c : ca) {
                   currenttime = currenttime.replace(""+c, "");
             }
      
                String nowtime = currentdate+currenttime;
                final long nowtimenow = Long.parseLong(nowtime);
                   
       
            
            
            
       
                   
            
                    if(nowtimenow ==alarm){
                   // System.out.println("ALARM!!!!!!");
                         try {
                   AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("/clock/resources/alarm.wav"));
                    Clip clip = AudioSystem.getClip();
                   clip.open(audioInputStream);
                   clip.start();
                   q.remove();
                     JOptionPane.showMessageDialog(frame, "Alarm Going Off", "ALARM!!!!", JOptionPane.INFORMATION_MESSAGE);
                   
                   
                    }
                          catch (Exception ex) {
        ex.printStackTrace();
        
       
         }
                   
               
                   }
                   
                    
                    
                    
                } catch (QueueUnderflowException ex) {
                  
                }
                   
       
                 }
        
        
        
    }


// And From your main() method or any other method
        Timer timer1 = new Timer();
        timer1.schedule(new checkalarm(), 0, 1000);
        
        
   
        
        
        
        
        
        
    }
    
    
    
    @Override
    public void update(Observable o, Object arg) {
        panel.repaint();
    }
    
    
    
}
