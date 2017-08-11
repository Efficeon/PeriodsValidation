import com.validator.PeriodsValidation;
import org.junit.Assert;
import org.junit.Test;

public class ValidatorTest {
    private PeriodsValidation periodsValidation = new PeriodsValidation();

    @Test
    public void testEmptyString(){
        String inputString = "";
        String expected = "Пересечения нет";
        String actual = periodsValidation.findIntersections(inputString);
        Assert.assertEquals("данные не введены", expected, actual);
    }

    @Test
    public void testTwoIntersections(){
        String inputString = "НачПериода1,НачПериода2,КонПериода1, КонПериода2";
        String expected = "НачПериода2, КонПериода1";
        String actual = periodsValidation.findIntersections(inputString);
        Assert.assertEquals("2 пересечения", expected, actual);
    }

    @Test
    public void testNoIntersections(){
        String inputString = "НачПериода1,КонПериода1,НачПериода2, КонПериода2";
        String expected = "Пересечения нет";
        String actual = periodsValidation.findIntersections(inputString);
        Assert.assertEquals("Пересечения нет", expected, actual);
    }

    @Test
    public void testMinusInfinity(){
        String inputString = " НачПериода4,КонПериода3,КонПериода4";
        String expected = "-бесконечность";
        String actual = periodsValidation.findIntersections(inputString);
        Assert.assertEquals("-бесконечность", expected, actual);
    }

    @Test
    public void testPlusInfinity(){
        String inputString = "НачПериода5,  КонПериода5,НачПериода6";
        String expected = "+бесконечность";
        String actual = periodsValidation.findIntersections(inputString);
        Assert.assertEquals("+бесконечность", expected, actual);
    }

    @Test
    public void testNestedPeriods(){
        String inputString = "НачПериода1,НачПериода2,КонПериода2, КонПериода1";
        String expected = "НачПериода2, КонПериода2";
        String actual = periodsValidation.findIntersections(inputString);
        Assert.assertEquals("2 пересечения", expected, actual);
    }

    @Test
    public void testMultiplePeriods(){
        String inputString = "НачПериода1,КонПериода1, НачПериода2,НачПериода3,НачПериода4,КонПериода3,КонПериода4, КонПериода2";
        String expected = "НачПериода3, НачПериода4, КонПериода3, КонПериода4";
        String actual = periodsValidation.findIntersections(inputString);
        Assert.assertEquals("4 пересечения", expected, actual);
    }
}
