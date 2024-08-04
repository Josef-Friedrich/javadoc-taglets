package rocks.friedrich.permalink_taglet;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a permalink, which is a URL that points to a specific location in
 * a repository.
 *
 * <p>
 * The permalink can include information about the host, owner, repository,
 * file, commit ID, and line range.
 * </p>
 *
 * @author Josef Friedrich
 */
public class Permalink
{
    private final URL url;

    private String display;

    private LineRange lineRange;

    /**
     * For example
     * {@code junit-team/junit5-samples/blob/425c27ecd9cefee5a1459e4cc9efd1c8390836e3/junit5-jupiter-starter-maven/pom.xml#L7}
     */
    private String path;

    private String host;

    private String owner;

    private String repo;

    private String file;

    private String commitId;

    public Permalink(String tagText)
    {
        String url = tagText;
        int firstSpace = tagText.indexOf(' ');
        if (firstSpace != -1)
        {
            url = tagText.substring(0, firstSpace);
            this.display = tagText.substring(firstSpace).trim();
        }
        try
        {
            this.url = new URL(url);
        }
        catch (MalformedURLException e)
        {
            throw new RuntimeException(e.getMessage());
        }
        path = this.url.getPath();
        host = this.url.getHost();
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
        if (display != null)
        {
            return display;
        }
        return String.format("%s/%s/%s %s %s", getHost(), getOwner(), getRepo(),
                getFile(), getLineRange().generateHtml());
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

    public String getShortCommitId()
    {
        return commitId.substring(0, 7);
    }

    public String generateHtmlLink()
    {
        return String.format("<a href=\"%s\">%s</a>", url.toString(),
                getDisplay());
    }
}
