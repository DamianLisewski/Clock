package clock;

import java.awt.event.*;
import javax.swing.Timer;

   /**
    *Basic controller class for the MVC framework
    *initialised variables for the listener , timer 
    * initialised variables for the model , view 
     */

public class Controller {
    
    ActionListener listener;
    Timer timer;
    
    Model model;
    View view;
    
       /**
    *constructor deals with initialising the controller class
    *within the class a timer runs every time a model is updated 
    *@param m
    *@param v 
     */
    public Controller(Model m, View v) {
        model = m;
        view  = v;
        
        listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.update();
            }
        };
        
        timer = new Timer(100, listener);
        timer.start();
    }
}