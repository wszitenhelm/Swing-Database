package CS2020.assignment2;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import CS2020.assignment2.Utils;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    //Artist artist = new Artist();
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue(Utils.checkIfBornOnWeekend("18 Feb 1996")); //weekend 
        assertTrue(Utils.checkIfBornOnWeekend("11 Dec 2004")); //weekend
        assertTrue(Utils.checkIfBornOnWeekend("4 Dec 2021")); //weekend
        assertTrue(Utils.checkIfBornOnWeekend("17 May 1998")); //weekend
        assertTrue(Utils.checkIfBornOnWeekend("12 Dec 2021")); //non weekend
    }
    
}
