/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package nlp.classes;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ethan
 */
public class UtilitiesTest {
    /**
     * Test of getMaxIndex method, of class Utilities.
     */
    @Test
    public void testGetMaxIndex() {
        System.out.println("getMaxIndex");
        double[] arr = {-0.5, 0.5, 1.5, 3.66, -4.5};
        int expResult = 3;
        int result = Utilities.getMaxIndex(arr);
        assertEquals(expResult, result);
    }
    
}
