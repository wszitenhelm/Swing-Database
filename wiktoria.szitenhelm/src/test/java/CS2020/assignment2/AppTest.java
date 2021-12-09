package CS2020.assignment2;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import CS2020.assignment2.Utils;

/**
 * Unit test for simple App.
 */

public class AppTest 
{
    String[] weekend = {"19 Apr 2014", "21 Jan 2012", "11 Dec 2021", "7 Dec 2014", "25 Feb 2017"}; 
    String[] notWeekend = {"8 Aug 2019", "15 Feb 2018", "18 Jul 2017", "5 Jan 2015", "9 Apr 2014"}; 
    
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

