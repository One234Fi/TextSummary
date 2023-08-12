/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package nlp.classes;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ethan
 */
public class StringCleanerTest {
    
    public StringCleanerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getSentences method, of class StringCleaner.
     */
    @Test
    public void testGetSentences() {
        System.out.println("getSentences");
        String content = "";
        StringCleaner instance = new StringCleaner();
        String[] expResult = null;
        String[] result = instance.getSentences(content);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cleanString method, of class StringCleaner.
     */
    @Test
    public void testCleanString() {
        System.out.println("cleanString");
        String string = "";
        StringCleaner instance = new StringCleaner();
        String expResult = "";
        String result = instance.cleanString(string);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateWordDictionary method, of class StringCleaner.
     */
    @Test
    public void testGenerateWordDictionary() {
        System.out.println("generateWordDictionary");
        String content = "";
        StringCleaner instance = new StringCleaner();
        String[] expResult = null;
        String[] result = instance.generateWordDictionary(content);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
