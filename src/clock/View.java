package clock;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
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
import net.fortuna.ical4j.data.*;
import net.fortuna.ical4j.model.*;
import net.fortuna.ical4j.model.component.*;

import queuemanager.*;


/**
 *
 * @author Damian Lisewski 
 * @version 1
 * 
 * Main class which deals with anything to do with the alarm system
 * it deals with the view of the GUI behind the alarm system
 * it also deals with the actions of some aspects of alarm system
 * 
 * 
 * 
 */
public class View implements Observer {
    
    ClockPanel panel;
    ActionListener listener;
    javax.swing.Timer timer;
    
    /**
     * listener called every second to check whether item stored in the head of the queue equals current time/date
     * if so the alarm will ring and a pop up will display to the user
     * 
     * listener called every second to check whether item stored in the head of the queue is not smaller than the current time/date
     * if so the alarm will be remove from the queue
     * 
     * method initilised frame for the main clock panel
     * method will create new frames for action relation to the deletion, creation , and viewing of alarm.
     * buttons,boxes ,labels and pop ups generated depending on different circumstances
     * 
     * 
     * @param model
     */
    public View(Model model) {
       final JFrame frame = new JFrame(); //create new frame
       panel = new ClockPanel(model); //create new panel with model input
        //frame.setContentPane(panel);
       frame.setTitle("Java Clock"); //set title of frame to text
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //on close exit system
       
       final PriorityQueue<Alarm> q; //initialize new queu with alarm item
       q = new SortedArrayPriorityQueue<>(5); //create new sorted array with size of 5
          
       
       
       
       Calendar date = Calendar.getInstance();     
          
       int day=0;
       int month=0;
       int year=0;
       int hour = 0;
       int minute = 0;
       int second = 0;
  
        //set current date and time values into seperate int variables 
       day = date.get(Calendar.DAY_OF_MONTH);
       month = date.get(Calendar.MONTH)+1;
       year = date.get(Calendar.YEAR);
       hour = date.get(Calendar.HOUR_OF_DAY);
       minute = date.get(Calendar.MINUTE);
       second = date.get(Calendar.SECOND);
        
       Date time = new Date();
       SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); 
       String currenttime = sdf.format(time);
       
       
       Date current = new Date();
       SimpleDateFormat dat = new SimpleDateFormat("yyyyMMdd");
       final String currentdate = dat.format(current);
        
       //code replaces : with nothing for formatied date
       String regx = ":";
                char[] ca = regx.toCharArray();
                 for (char c : ca) {
                   currenttime = currenttime.replace(""+c, "");
             }

        String Checkdate = currentdate+currenttime; //concationate string adding currentdate and currenttime
        final long now = Long.parseLong(Checkdate); //parse string checkdate to long

        Container pane = frame.getContentPane();
 
        //set size of panel in the frame and center it
        panel.setPreferredSize(new Dimension(400, 400));
        pane.add(panel, BorderLayout.CENTER);
         
        
     
        
        //create a menubar and add it to the main frame
        final JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        
        //create and add a new section to the menu called file
        JMenu menu = new JMenu("Program");
        menuBar.add(menu);
        
        //create new and add and item to menu part of the menu bar
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        menu.add(exitMenuItem);
        
        //create and add a new section to the menu
        menu = new JMenu("Alarm");
        menuBar.add(menu);

        //create and add a new menu item to the menu
        JMenuItem addalarmitem = new JMenuItem("Set Alarm");
        menu.add(addalarmitem);
        
        final JFrame addalarm = new JFrame(); //create new frame called addalarm
        addalarm.setLayout(null); //set layout of frame to null
        addalarm.setSize(400, 400); //set size of frame
         
        JLabel namelabel =new JLabel("Alarm Name");  //set new label with a text 
        namelabel.setBounds(160,10,100,50); //set location of the name label
        final JTextField alarmname = new JTextField("",20); //set new box  
        alarmname.setBounds(145,60,100,30); //set location of the name box
       
        final String dash = "/"; //set string
        final String dot = ":"; //set string
        final String space = " "; //set string 
        
        
        final JLabel currentdatelabel =new JLabel(""); //set new label without a text
        currentdatelabel.setBounds(150,250,120,50); //set location of current date time label
        
        JLabel datelabel =new JLabel("Date"); //set new label with a text 
        datelabel.setBounds(80,110,50,50); //set location of the date label
        JLabel dateformat =new JLabel("(yyyymmdd)");  //set new label with a text
        dateformat.setBounds(60,150,100,50); //set location of the dateformat label
        
        JLabel timelabel =new JLabel("Time"); //set new label with a text  
        timelabel.setBounds(280,110,50,50); //set location for the date format label
        JLabel timeformat =new JLabel("(hh:mm:ss)"); //set new label with a text 
        timeformat.setBounds(270,150,100,50); //set location for timeformat label

        final JTextField Date = new JTextField("",20); //initialise box for date
        Date.setBounds(50,200,100,30); //set location of date box 
        final JTextField Time = new JTextField("",20); //initialise box for time
        Time.setBounds(250,200,100,30); //set location of time box 
        
        addalarm.add(currentdatelabel); // add current date label to addalarm frame
        addalarm.add(namelabel); //add alarm name label to the addalarm frame
        addalarm.add(alarmname); //add alarm name box to the addalarm frame
         
        addalarm.add(datelabel); //add date label to addalarm frame
        addalarm.add(dateformat); //add date label to addalarm frame
        addalarm.add(Date); //add date box to addalarm frame
         
        addalarm.add(timelabel); //add time label to addalarm frame
        addalarm.add(timeformat); //add time format label to addalarm frame
        addalarm.add(Time); //add time box to addalarm frame
       
        JButton Addalarmbutton = new JButton("Add"); //create new button called Addalarmbutton with text "add"
        Addalarmbutton.setBounds(100,300,100,50); //set the location of the button
        addalarm.add(Addalarmbutton);//add add button to the addalarm frame
        JButton Clear = new JButton("Reset"); //create new button called clear with text "Reset"
        Clear.setBounds(200,300,100,50); //set the location of clear button
        addalarm.add(Clear); //add clear button to the addalarm frame
           
        
        
        
             //create and add a new menu item to the menu
        JMenuItem editalarmitem = new JMenuItem("Edit Closest Alarm");
        menu.add(editalarmitem);
        
        final JFrame editalarm = new JFrame(); //create new frame called addalarm
        editalarm.setLayout(null); //set layout of frame to null
        editalarm.setSize(400, 400); //set size of frame
         
        JLabel editnamelabel =new JLabel("Alarm Name");  //set new label with a text 
        editnamelabel.setBounds(160,10,100,50); //set location of the name label
        final JTextField editnamefield = new JTextField("",20); //set new box  
        editnamefield.setBounds(145,60,100,30); //set location of the name box
       
        final String editdash = "/"; //set string
        final String editdashdot = ":"; //set string
        final String editdashspace = " "; //set string 
        
        
        final JLabel editcurrentdatelabel =new JLabel(""); //set new label without a text
        editcurrentdatelabel.setBounds(150,250,120,50); //set location of current date time label
        
        JLabel editdatelabel =new JLabel("Date"); //set new label with a text 
        editdatelabel.setBounds(80,110,50,50); //set location of the date label
        JLabel editdateformat =new JLabel("(yyyymmdd)");  //set new label with a text
        editdateformat.setBounds(60,150,100,50); //set location of the dateformat label
        
        JLabel edittimelabel =new JLabel("Time"); //set new label with a text  
        edittimelabel.setBounds(280,110,50,50); //set location for the date format label
        JLabel edittimeformat =new JLabel("(hh:mm:ss)"); //set new label with a text 
        edittimeformat.setBounds(270,150,100,50); //set location for timeformat label

        final JTextField editDate = new JTextField("",20); //initialise box for date
        editDate.setBounds(50,200,100,30); //set location of date box 
        final JTextField editTime = new JTextField("",20); //initialise box for time
        editTime.setBounds(250,200,100,30); //set location of time box 
        
        editalarm.add(editcurrentdatelabel); // add current date label to editalarm frame
        editalarm.add(editnamelabel); //add alarm name label to the editalarm frame
        editalarm.add(editnamefield); //add alarm name box to the editalarm frame
         
        editalarm.add(editdatelabel); //add date label to editalarm frame
        editalarm.add(editdateformat); //add date label to editalarm frame
        editalarm.add(editDate); //add date box to editalarm frame
         
        editalarm.add(edittimelabel); //add time label to editalarm frame
        editalarm.add(edittimeformat); //add time format label to editalarm frame
        editalarm.add(editTime); //add time box to editalarm frame
       
        JButton Editalarmbutton = new JButton("Edit"); //create new button called editalarmbutton with text "edit"
        Editalarmbutton.setBounds(145,300,100,50); //set the location of the button
        editalarm.add(Editalarmbutton);//add edit button to the addalarm frame
        
        
        
        JMenuItem deletealarmitem = new JMenuItem("Delete Closest Alarm"); //new Jmenuitem called deletealarmitem , set text to deleteitem
        menu.add(deletealarmitem); //add delete menu item to the menu
        
        
        
        final JMenuItem label = new JMenuItem(""); 
        menuBar.add(label);
        //final JFrame viewalarm = new JFrame(); //initialize new frame and call it viewalarm
    //    viewalarm.setTitle("View Alarm"); //set title of viewalarm frame to "View Alarm"
      //  viewalarm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //dispose of frame once closed
      //  viewalarm.setSize(400, 400); //set size of frame to 400 width and 400 height
 
        
         /**
         *listener for the add alarm menu item. 
         *once pressed the program runs the code 
         */  
        addalarmitem.addActionListener(new ActionListener() { 
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            addalarm.setVisible(true);//sets frame addalarm visible to true
            
        }
 
        
        }
        
        
        
        );
        
               /**
         *listener for the add alarm menu item. 
         *once pressed the program runs the code 
         */  
        editalarmitem.addActionListener(new ActionListener() { 
        @Override
        public void actionPerformed(ActionEvent e) 
        {
          //sets frame addalarm visible to true
            try {
                editalarm.setVisible(true);  
                editnamefield.setText(q.head().getName());
                
                String editd = Long.toString(q.head().getAlarm());
               editd= editd.substring(0,8);
               String editt = Long.toString(q.head().getAlarm());
               editt= editt.substring(8,14);
               
               editt = insertPeriodically(editt, ":",  2);
              
                 
                 
               
               editDate.setText(editd); 
               editTime.setText(editt);
                
                
            } catch (QueueUnderflowException ex) {
                JOptionPane.showMessageDialog(frame, "No Alarm to Edit", "Empty Head", JOptionPane.ERROR_MESSAGE);
                editalarm.dispose(); 
            }
        }
 
        
        }
        
        
        
        );
        
    /**
     *
     * @param text
     * @param insert
     * @param period
     * @return
     */

        
        
         /**
         *listener for the add alarm frame. 
         *once closed the program runs code below 
  
        */
         addalarm.addWindowListener(new WindowAdapter() {
 
        @Override
 
        public void windowClosing(WindowEvent e) {
 
        Date.setText("");
        Time.setText("");
        alarmname.setText("");
        addalarm.dispose();
        
 
        }
 
  });
            /**
         *listener for the delete alarm menu item. 
         *once pressed the program runs the code 
         */  
        deletealarmitem.addActionListener(new ActionListener() { 
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            try {
                q.remove();
            } catch (QueueUnderflowException ex) {
                JOptionPane.showMessageDialog(frame, "No Alarms To Delete", "Empty Alarms Queue", JOptionPane.ERROR_MESSAGE);
            }
        }
        });
        
         /**
         *listener for the view alarm menu item. 
         *once pressed the program runs the code before
         */  
     
            
    /**
   *listener for the exit menu item on the main frame. 
   *exits program once pressed
   */     
         
    exitMenuItem.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
    System.exit(0);
    }
    });
              
              
              
   /**
   *listener for the addbutton on the addalarm frame. 
   *if pressed it validated the information entered by user and performed pop ups if incorrect.
   *if all data is correct then alarm is added to the array.
   */                              
  Addalarmbutton.addActionListener(new ActionListener(){
  @Override
  public void actionPerformed(ActionEvent e)
  {
        String InputDate = Date.getText(); //get date entered by user in the date box
        String InputTime = Time.getText(); //get time entered by user in the time box
        DateFormat dateformat = new SimpleDateFormat("yyyyMMdd"); //set format of date
        DateFormat timeformat = new SimpleDateFormat("HH:mm:ss"); //set format of time
        dateformat.setLenient(false); //non lenient format
        timeformat.setLenient(false); //non lenient format
        
        
     
          
        //try following
        try 
        {
                dateformat.parse(InputDate); //parse inputed date of user into the dataformat specified
                LocalTime.parse(InputTime); //parse inputed date of user into the dataformat specified
                String InputAlarm = alarmname.getText(); //get name of alarm entered by user and store in string variable
            
                //removes : from time formated variable
                String regx = ":";
                char[] ca = regx.toCharArray();
                 for (char c : ca) {
                   InputTime = InputTime.replace(""+c, "");
                }
         
                String ConcatAlarm = InputDate+InputTime; //concationation of inputdate and inputtime stored in a variable

                long priority = Long.parseLong(ConcatAlarm.substring(ConcatAlarm.lastIndexOf(' ') + 1)); // parses priority of alarm from string to long
                
                Alarm alarm = new Alarm(InputAlarm,priority); //create new alarm item with input name and priority.
                
                 //try following.
                try 
                {
                    
                    if(priority<=now){ //if the alarm entered by user is lower then the current time then
                    
                    // displayed pop up if date entered by user is before the current time set
                    JOptionPane.showMessageDialog(addalarm, "Date needs to be set in the future : Set Date Time in futute", "Wrong Date", JOptionPane.ERROR_MESSAGE);
                    }
                    
                    else
                    {
                        q.add(alarm, priority); //adds alarm with name and priority to the array;
                        String MessageAdd = "Adding a Alarm called " + alarm.getName() + " with the Date" + InputDate + " and Time " +InputTime ; // message to display to user stating datetime and name of alarm
                        JOptionPane.showMessageDialog(addalarm, MessageAdd, "Added Alarm", JOptionPane.INFORMATION_MESSAGE); //pop up informing user that the alarm is added
                    }
                } //displays message if the queue is full
              catch (QueueOverflowException a) 
               {
                     JOptionPane.showMessageDialog(addalarm, "Queue is Full : Remove Some Alarms!", "Queue Full", JOptionPane.INFORMATION_MESSAGE);
                    
               }

          //shows pop ups if the formats entered by user are invalid  
        } catch (ParseException | DateTimeParseException eg) {

           JOptionPane.showMessageDialog(addalarm, "Wrong Format (yyyyMMdd)", "Date", JOptionPane.ERROR_MESSAGE); 
           JOptionPane.showMessageDialog(addalarm, "Wrong Format (HH:mm:ss)", "Time", JOptionPane.ERROR_MESSAGE);
             
        }
        }
        });
      /**
    * Listener method for clear button . 
    * if clear button is pressed in the addalarm frame run certain code.
    * code will clear all text fields and dispose of the current frame 
    * resets the frame by dispose method and sets it to visible again
    */     
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
              
 
    
     /**
   *listener for the addbutton on the addalarm frame. 
   *if pressed it validated the information entered by user and performed pop ups if incorrect.
   *if all data is correct then alarm is added to the array.
   */                              
  Editalarmbutton.addActionListener(new ActionListener(){
  @Override
  public void actionPerformed(ActionEvent e)
  {
        String InputDate = editDate.getText(); //get date entered by user in the date box
        String InputTime = editTime.getText(); //get time entered by user in the time box
        DateFormat dateformat = new SimpleDateFormat("yyyyMMdd"); //set format of date
        DateFormat timeformat = new SimpleDateFormat("HH:mm:ss"); //set format of time
        dateformat.setLenient(false); //non lenient format
        timeformat.setLenient(false); //non lenient format
        
        
     
          
        //try following
        try 
        {
                dateformat.parse(InputDate); //parse inputed date of user into the dataformat specified
                LocalTime.parse(InputTime); //parse inputed date of user into the dataformat specified
                String InputAlarm = editnamefield.getText(); //get name of alarm entered by user and store in string variable
            
                //removes : from time formated variable
                String regx = ":";
                char[] ca = regx.toCharArray();
                 for (char c : ca) {
                   InputTime = InputTime.replace(""+c, "");
                }
         
                String ConcatAlarm = InputDate+InputTime; //concationation of inputdate and inputtime stored in a variable

                long priority = Long.parseLong(ConcatAlarm.substring(ConcatAlarm.lastIndexOf(' ') + 1)); // parses priority of alarm from string to long
                
                q.remove();
                Alarm alarm = new Alarm(InputAlarm,priority); //create new alarm item with input name and priority.
                
                 //try following.
                try 
                {
                    
                    if(priority<=now){ //if the alarm entered by user is lower then the current time then
                    
                    // displayed pop up if date entered by user is before the current time set
                    JOptionPane.showMessageDialog(addalarm, "Date needs to be set in the future : Set Date Time in futute", "Wrong Date", JOptionPane.ERROR_MESSAGE);
                    }
                    
                    else
                    {
                        q.add(alarm, priority); //edits alarm with name and priority to the array;
                        String MessageAdd = "Editing a Alarm and calling it " + alarm.getName() + " with the Date" + InputDate + " and Time " +InputTime ; // message to display to user stating datetime and name of alarm
                        JOptionPane.showMessageDialog(addalarm, MessageAdd, "Edited Alarm", JOptionPane.INFORMATION_MESSAGE); //pop up informing user that the alarm is added
                    }
                } //displays message if the queue is full
              catch (QueueOverflowException a) 
               {
                     JOptionPane.showMessageDialog(addalarm, "Queue is Full : Remove Some Alarms!", "Queue Full", JOptionPane.INFORMATION_MESSAGE);
                    
               }

          //shows pop ups if the formats entered by user are invalid  
        } catch (ParseException | DateTimeParseException egf) {

           JOptionPane.showMessageDialog(addalarm, "Wrong Format (yyyyMMdd)", "Date", JOptionPane.ERROR_MESSAGE); 
           JOptionPane.showMessageDialog(addalarm, "Wrong Format (HH:mm:ss)", "Time", JOptionPane.ERROR_MESSAGE);
             
        } catch (QueueUnderflowException ex) {
          JOptionPane.showMessageDialog(addalarm, "Head Empty", "Empty", JOptionPane.ERROR_MESSAGE);
      }
        }
        });
      /**
    * Listener method for clear button . 
    * if clear button is pressed in the addalarm frame run certain code.
    * code will clear all text fields and dispose of the current frame 
    * resets the frame by dispose method and sets it to visible again
    */     
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
        
        frame.pack(); //pack frame 
        frame.setVisible(true); //set frame visability to true
        
      /**
    * Method used to check whether alarm stored in the head is equal to the current time. 
    * if so the alarm will display a message and play a sound.
    */         
    class checkalarm extends TimerTask {
    @Override
    public void run() {
        
        //try following
        try{
          
         //create new instance of calender to collect date time values   
         Calendar datecheck = Calendar.getInstance();
             
        //initialize variables to be used to store datetime values     
        int day=0;
        int month=0;
        int year=0;
        int hour = 0;
        int minute = 0;
        int second = 0;
  
        //set current date and time values into seperate int variables 
        day = datecheck.get(Calendar.DAY_OF_MONTH);
        month = datecheck.get(Calendar.MONTH)+1;
        year = datecheck.get(Calendar.YEAR);
        hour = datecheck.get(Calendar.HOUR_OF_DAY);
        minute = datecheck.get(Calendar.MINUTE);
        second = datecheck.get(Calendar.SECOND);
        
 
        //converting integers int strings and assigning them to values.   
        String day1 = Integer.toString(day); 
        String month1 = Integer.toString(month); 
        String year1 = Integer.toString(year); 
        String hour1 = Integer.toString(hour); 
        String minute1 = Integer.toString(minute); 
        String second1 = Integer.toString(second);
            
       String currentdatestring =(day1+dash+month1+dash+year1+space+hour1+dot+minute1+dot+second1);  //use date and time variables to create a string of current dateandtime
       currentdatelabel.setText(currentdatestring); //sets label in the addalarm frame text to currentdatestring value
       
       //sets long alarm to the current head items alarm value
       long alarm = q.head().getAlarm();
                       
       Date time = new Date(); //sets new date instance to be used for the current time
        
       SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); //sets format for the date
       
       String currenttime = sdf.format(time);  //sets string to formated current time
  
       Date current = new Date(); //sets new date instance to be used for the current date
        
       SimpleDateFormat dat = new SimpleDateFormat("yyyyMMdd"); //sets format for the date
       
       
       final String currentdate = dat.format(current); //sets string to formated currentdate
        
       //used for removing : from the time formated variable
         String regx = ":";
                char[] ca = regx.toCharArray();
                 for (char c : ca) {
                   currenttime = currenttime.replace(""+c, "");
             }
                 
         String nowtime = currentdate+currenttime; //set string to concationation of currenttime and date.
         
         final long nowtimenow = Long.parseLong(nowtime); //parses string variable into a long 

         if(nowtimenow ==alarm) //if current time equal the time of the alarm in the head 
            {

                   //try following.
                   try 
                   {
                   AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("/clock/resources/alarm.wav"));//creates a new audio input stream and selects the location of the file.
                   Clip clip = AudioSystem.getClip(); //collects clip specified in the audio stream
                   clip.open(audioInputStream); //opens the file which plays the alarm sound
                   clip.start(); //starts alarm clock timer
                   //q.remove();
                   JOptionPane.showMessageDialog(frame, "Alarm Going Off", "ALARM!!!!", JOptionPane.INFORMATION_MESSAGE); //pop up to show alarm going off

                    }
             catch (Exception ex) {ex.printStackTrace();}//catches second try with printstacktrace
 
            }//closes if statement
         
        } //closes first try. 
        catch (QueueUnderflowException ex)
        {
          //displays errror message if array is empty
             // JOptionPane.showMessageDialog(frame, "Alarm Array Empty :No Alarm Set", "Alarms", JOptionPane.ERROR_MESSAGE);             
        }
                  
        }
 
    }
    
     /**
    * Method used to check whether alarms stored in the array are not set before the current time. 
    * if they are found they are removed from the array.
    */   
    class checkarray extends TimerTask {
    @Override
    public void run() {
        //try following
        try{
       //create a new instance of calender to use for fetching current date and time     
       Calendar datecheck = Calendar.getInstance();
        
       //int used to store values of current date and time      
       int day=0;
       int month=0;
       int year=0;
       int hour = 0;
       int minute = 0;
       int second = 0;
   
       //set values of ints to current date and time values.     
       day = datecheck.get(Calendar.DAY_OF_MONTH);
       month = datecheck.get(Calendar.MONTH)+1; //sets current month into a int variable (+ plus 1 due to months starting at 0)
       year = datecheck.get(Calendar.YEAR);
       hour = datecheck.get(Calendar.HOUR_OF_DAY);
       minute = datecheck.get(Calendar.MINUTE);
       second = datecheck.get(Calendar.SECOND);
          
       //converts interger values used to store datetime values into strings.    
       String day1 = Integer.toString(day); 
       String month1 = Integer.toString(month); 
       String year1 = Integer.toString(year); 
       String hour1 = Integer.toString(hour); 
       String minute1 = Integer.toString(minute); 
       String second1 = Integer.toString(second);
            
       String currentdatestring =(day1+dash+month1+dash+year1+space+hour1+dot+minute1+dot+second1); //creates the currentdate string using variables and characters stored in previous date variables
       currentdatelabel.setText(currentdatestring); //sets text label on the addalarm frame to the currentdatestring
       
       long alarm = q.head().getAlarm(); // sets new long variable to the current heads alarm value

       Date time = new Date(); //creates new date variable to store the current time
        
       SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); //sets format for the current time

       String currenttime = sdf.format(time);  //creates new date variable to store the current time
       
       Date current = new Date(); //creates new date variable to store the current date
        
       SimpleDateFormat dat = new SimpleDateFormat("yyyyMMdd");//sets format for the current date
       
       //set string to formated version of date sorted in current
       final String currentdate = dat.format(current);
       //this replaces and removes the : in the time insert   
       String regx = ":";
                char[] ca = regx.toCharArray();
                 for (char c : ca) {
                   currenttime = currenttime.replace(""+c, "");
             }
      
        String nowtime = currentdate+currenttime; // set now string to concatination of currentdate and currenttime
        final long nowtimenow = Long.parseLong(nowtime); //parse nowtime as a long and store in the nowtimenow variable.

        if(alarm < nowtimenow) //if current item in the heads time is smaller than the current time then run.
        {
        
        try 
        {   
        q.remove(); //removes item that is currently in the head
        }
        
        catch (Exception ex) // catch for removing error | if empty
        {ex.printStackTrace();} //displays error in console
        
        }//ends second try
    
        } //ends first try
            // catches the error if array is empty
            catch (QueueUnderflowException ex) 
           {
               //displays errror message if array is empty
             // JOptionPane.showMessageDialog(frame, "Alarm Array Empty :No Alarm Set", "Alarms", JOptionPane.ERROR_MESSAGE);    
           }
       }
        
        
        
    }
    
       class checknext extends TimerTask {
    @Override
    public void run() {
        
    
        
        
      try{
            
             if(q.isEmpty()){label.setText("");} 
          
          
             else{
        String nextalarm = Long.toString(q.head().getAlarm());
        //nextalarm= nextalarm.substring(0, 8);
        
         SimpleDateFormat df_in = new SimpleDateFormat("yyyyMMddhhmmss");
          String date1 = null;
        //output date format
        SimpleDateFormat df_output = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = df_in.parse(nextalarm);
        date1 = df_output.format(date);
        date1 = "            [Next Alarm Is Set For: "+date1+"]" ;
        label.setText(date1);
          
             }}catch(QueueUnderflowException  | ParseException p){
        
        }
    
    }
       }
       
         /**
         * This timer is used to for the method which checks if the time of the head alarm matches current time. 
         * This is used  as a timer to check whether any item in the array has a time lower than the current
         *  the timer is set with no delay and runs the checkalarm() method every second
          */
       
           
        Timer timer1 = new Timer();
        timer1.schedule(new checkalarm(), 0, 1000);
        
        
          /**
         * This timer is used to for the method which checks the object array for items. 
         * This is used  as a timer to check whether any item in the array has a time lower than the current
         *  the timer is set with no delay and runs the checkarray() method every second
  
          */
       
        Timer timer2 = new Timer();
        timer2.schedule(new checkarray(), 0, 1000);
        
        Timer timer3 = new Timer();
        timer3.schedule(new checknext(), 0,1000);
     
        
        
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
        @Override
        public void run() {
            
               String st = q.toString();
               st = st.replace("[","").replace("]","");
                st = st.replace("(","").replace(")","");
                String[] arr = st.split(", ");
               System.out.println(Arrays.toString(arr));
               
             // ArrayList arraylist = new ArrayList<>(Arrays.asList(arr));
              

              
         // VAlarm alarm = new VAlarm();
            
        }
    }, "Shutdown-thread"));
        
        
        
    }
    
    
     /**
    * repaints the panel with the new clock data. 
    * this is used to update the clock panel with new time data

    * @param  o  link to object of interest to observer
    * @param  arg selects object to update
    */
    @Override
    public void update(Observable o, Object arg) {
        panel.repaint();
    }
    
        
    
     /**
    * code used for periodically inserting chars into a string. 
    * Reference 
    * https://stackoverflow.com/questions/537174/putting-char-into-a-java-string-for-each-n-characters
    * code by Jon Skeet
    * 2009
    *
     * @param text
     * @param insert
     * @param period
     * @return 
    */
    
    
    public static String insertPeriodically(
    String text, String insert, int period)
{
    StringBuilder builder = new StringBuilder(
         text.length() + insert.length() * (text.length()/period)+1);

    int index = 0;
    String prefix = "";
    while (index < text.length())
    {
        // Don't put the insert in the very first iteration.
        // This is easier than appending it *after* each substring
        builder.append(prefix);
        prefix = insert;
        builder.append(text.substring(index, 
            Math.min(index + period, text.length())));
        index += period;
    }
    return builder.toString();
}
  
}
