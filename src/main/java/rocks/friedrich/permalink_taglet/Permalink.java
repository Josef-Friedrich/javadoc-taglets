package rocks.friedrich.permalink_taglet;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Permalink
{
    private final URL url;

    private final String display;

    private LineRange lineRange;

    /**
     * {@code junit-team/junit5-samples/blob/425c27ecd9cefee5a1459e4cc9efd1c8390836e3/junit5-jupiter-starter-maven/pom.xml#L7}
     */
    private String path;

    private String host;

    private String owner;

    private String repo;

    private String file;

    private String commitId;

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
        path = this.url.getPath();
        String[] pathSegments = path.split("/");
        owner = pathSegments[1];
        repo = pathSegments[2];
        Pattern pattern = Pattern.compile("[0-9a-f]{40}",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(path);
        if (matcher.find())
        {
            commitId = matcher.group(0);
            file = path.substring(matcher.end() + 1);
        }
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

    public String getHost()
    {
        return host;
    }

    public String getOwner()
    {
        return owner;
    }

    public String getRepo()
    {
        return repo;
    }

    public String getFile()
    {
        return file;
    }

    public String getCommitId()
    {
        return commitId;
    }

    public String generateHtmlLink()
    {
        return String.format("<a href=\"%s\">%s</a>", url.toString(), display);
    }
}
