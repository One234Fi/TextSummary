/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package nlp.classes;

import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author ethan
 */
public class UtilitiesTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
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

    /**
     * Test of getMaxIndex method, of class Utilities.
     */
    @Test
    public void testGetMaxIndex_doubleArr() {
        System.out.println("getMaxIndex");
        double[] arr = null;
        int expResult = 0;
        int result = Utilities.getMaxIndex(arr);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaxIndex method, of class Utilities.
     */
    @Test
    public void testGetMaxIndex_intArr() {
        System.out.println("getMaxIndex");
        int[] arr = null;
        int expResult = 0;
        int result = Utilities.getMaxIndex(arr);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMagnitude method, of class Utilities.
     */
    @Test
    public void testGetMagnitude_doubleArr() {
        System.out.println("getMagnitude");
        double[] vector = null;
        double expResult = 0.0;
        double result = Utilities.getMagnitude(vector);
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMagnitude method, of class Utilities.
     */
    @Test
    public void testGetMagnitude_intArr() {
        System.out.println("getMagnitude");
        int[] vector = null;
        double expResult = 0.0;
        double result = Utilities.getMagnitude(vector);
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDotProduct method, of class Utilities.
     */
    @Test
    public void testGetDotProduct_intArr_intArr() {
        System.out.println("getDotProduct");
        int[] vector1 = null;
        int[] vector2 = null;
        int expResult = 0;
        int result = Utilities.getDotProduct(vector1, vector2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDotProduct method, of class Utilities.
     */
    @Test
    public void testGetDotProduct_doubleArr_doubleArr() {
        System.out.println("getDotProduct");
        double[] vector1 = null;
        double[] vector2 = null;
        double expResult = 0.0;
        double result = Utilities.getDotProduct(vector1, vector2);
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStopWords method, of class Utilities.
     */
    @Test
    public void testGetStopWords() {
        System.out.println("getStopWords");
        Set<String> expResult = null;
        Set<String> result = Utilities.getStopWords();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
