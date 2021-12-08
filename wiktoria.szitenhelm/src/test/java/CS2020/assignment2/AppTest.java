package CS2020.assignment2;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import CS2020.assignment2.Utils;

/**
 * Unit test for simple App.
 */

public class AppTest 
{
    String[] weekend = {"12 Apr 2014", "21 Jan 2012", "4 Dec 2021", "14 Dec 2014", "18 Feb 2017"}; 
    String[] notWeekend = {"1 Aug 2019", "8 Feb 2018", "25 Jul 2017", "12 Jan 2015", "2 Apr 2014"}; 
    
    /**
     * Rigorous Test :-)
     */
    
    @Test
    public void shouldAnswerWithTrue()
    {
        for (int i=0; i < weekend.length; i++) {
            assertTrue(Utils.checkIfBornOnWeekend(weekend[i])); //check if returns true 
        } 
         for (int i=0; i < notWeekend.length; i++) {
             assertTrue(!(Utils.checkIfBornOnWeekend(notWeekend[i]))); //check if returns false 
         }
    }
    
}

