package rocks.friedrich.permalink_taglet;

public class LineRange
{
    private final int begin;

    private final int end;

    public LineRange(String query)
    {
        String[] segments = query.split("-");
        int[] lines = new int[segments.length];
        for (int i = 0; i < segments.length; i++)
        {
            lines[i] = Integer.parseInt(segments[i].substring(1));
        }
        begin = lines[0];
        if (segments.length == 1)
        {
            end = -1;
        }
        else
        {
            end = lines[1];
        }
    }

    public int getBegin()
    {
        return begin;
    }

    public int getEnd()
    {
        return end;
    }

    public String generateHtml()
    {
        if (end == -1)
        {
            return "Line " + begin;
        }
        else
        {
            return "Lines " + begin + " - " + end;
        }
    }
}
