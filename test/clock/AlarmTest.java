/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Damian
 */
public class AlarmTest {
    
    public AlarmTest() {
    }

 
    
    @Before
    public void setUp() {
    }
    
 

    /**
     * Test of getName method, of class Alarm.
    
     */
    @Test
    public void testGetName() {
            System.out.println("Alarm Test:");
         System.out.println("GetName()");
        Alarm instance;
        long alarms= 20200515200000L;
        instance = new Alarm("Damian",alarms);
        String expResult = "Damian";
        String result = instance.getName();
        
        ///System.out.println(result);
        //System.out.println(expResult);
        if(expResult == null ? result != null : !expResult.equals(result))
        {
            System.out.println("Failed");
            
        }
        // TODO review the generated test code and remove the default call to fail.
                else
        {
            System.out.println("Passed");
        }
       
    }
    
       @Test
    public void testGetAlarm() {
            System.out.println("Alarm Test:");
         System.out.println("GetAlarm()");
        Alarm instance;
        long alarms= 20200515200000L;
        instance = new Alarm("Damian",alarms);
        String expResult = "20200515200000";
        Long alarmed = instance.getAlarm();
        String result = Long.toString(alarmed);
        
        ///System.out.println(result);
        //System.out.println(expResult);
        if(expResult == null ? result != null : !expResult.equals(result))
        {
            System.out.println("Failed");
            
        }
        // TODO review the generated test code and remove the default call to fail.
                else
        {
            System.out.println("Passed");
        }
       
    }
    


    
     //* Test of toString method, of class Alarm.
     
    @Test
    public void testToString() {
          System.out.println("Person Test:");
       System.out.println("toString()");
        Alarm instance;
        instance = new Alarm("Damian" ,20200515200000L);
       String expResult = "Damian";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if(expResult == null ? result != null : !expResult.equals(result))
        {
            System.out.println("Failed");
            
        }
        // TODO review the generated test code and remove the default call to fail.
                else
        {
            System.out.println("Passed");
        }
    }
    
}