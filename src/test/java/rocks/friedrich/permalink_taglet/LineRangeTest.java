package rocks.friedrich.permalink_taglet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LineRangeTest
{
    @Test
    void testBeginAndEnd()
    {
        LineRange range = new LineRange("L1-L2");
        assertEquals(range.getBegin(), 1);
        assertEquals(range.getEnd(), 2);
    }

    @Test
    void getBeginOnly()
    {
        LineRange range = new LineRange("L123");
        assertEquals(range.getBegin(), 123);
        assertEquals(range.getEnd(), -1);
    }

    @Test
    void generateHtml()
    {
        LineRange range = new LineRange("L1-L2");
        assertEquals(range.toString(), "Lines 1 - 2");
    }
}
