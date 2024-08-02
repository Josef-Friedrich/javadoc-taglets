package rocks.friedrich.permalink_taglet;

import java.net.MalformedURLException;
import java.net.URL;

public class Permalink
{
    private final URL url;

    private final String display;

    private LineRange lineRange;

    private String host;

    private String owner;

    private String repo;

    private String file;

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
        // junit-team/junit5-samples/blob/425c27ecd9cefee5a1459e4cc9efd1c8390836e3/junit5-jupiter-starter-maven/pom.xml#L7
        String path = this.url.getPath();
        String[] pathSegments = path.split("/");
        owner = pathSegments[0];
        repo = pathSegments[1];
        // "[0-9a-f]{40}";
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
