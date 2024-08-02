package rocks.friedrich.permalink_taglet;

import java.net.MalformedURLException;
import java.net.URL;

public class Permalink
{
    private final URL url;

    private final String display;

    private LineRange lineRange;

    public Permalink(String tagText) throws MalformedURLException
    {
        String url = tagText;
        String display = tagText;
        int firstSpace = tagText.indexOf(' ');
        if (firstSpace != -1)
        {
            url = tagText.substring(0, firstSpace);
            display = tagText.substring(firstSpace).trim();
        }
        this.url = new URL(url);
        this.display = display;
        String ref = this.url.getRef();
        if (ref != null)
        {
            lineRange = new LineRange(ref);
        }
    }

    public URL getUrl()
    {
        return url;
    }

    public String getDisplay()
    {
        return display;
    }

    public LineRange getLineRange()
    {
        return lineRange;
    }

    public String generateHtmlLink()
    {
        return String.format("<a href=\"%s\">%s</a>", url.toString(), display);
    }
}
