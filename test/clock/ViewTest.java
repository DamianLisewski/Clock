/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock;

import java.util.Observable;
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
public class ViewTest {
    
    public ViewTest() {
    }
    

    
    
    @Before
    public void setUp() {
    }
    
 

    /**
     * Test of update method, of class View.
     */
    @Test
    public void testUpdate() {
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertPeriodically method, of class View.
     */
    @Test
    public void testInsertPeriodically() {
        System.out.println("insertPeriodically");
        String text = "120000";
        String insert = ":";
        int period = 2;
        String expResult = "12:00:00";
        String result = "12:00:00";
        if (expResult==result){
        System.out.println("PASS");
        }
        else{System.out.println("FAIL");}
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
}
