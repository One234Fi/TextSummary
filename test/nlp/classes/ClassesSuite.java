/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4Suite.java to edit this template
 */
package nlp.classes;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author ethan
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({nlp.classes.CosineSimilarityTest.class, nlp.classes.ParallelizedCosineSimilarityTest.class, nlp.classes.UtilitiesTest.class, nlp.classes.RAKERelevanceTest.class, nlp.classes.ParallelizedStringVectorizerTest.class, nlp.classes.StringVectorizerTest.class, nlp.classes.StringSelectorTest.class, nlp.classes.StringCleanerTest.class})
public class ClassesSuite {

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
    
}
