/*
 * Copyright (c) 2024 Josef Friedrich.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package rocks.friedrich.taglets;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;

/**
 * Represents a link to a repository file in a web view, which is a URL that
 * points to a specific location in a repository.
 *
 * <p>
 * The permalink can include information about the host, owner, repository,
 * file, commit ID, and line range.
 * </p>
 *
 * For example:
 * {@code https://github.com/Josef-Friedrich/javadoc-taglets/blob/066acfb1c11107be603580b9a603cf3c892e3c60/src/main/java/rocks/friedrich/permalink_taglet/PermalinkTaglet.java#L23-L42}
 *
 * @author Josef Friedrich
 */
public class Repolink
{
    /**
     * The complete URL of the repository link.
     */
    private final URL url;

    /**
     * The host name of the repository link URL, for example:
     * {@code github.com}.
     *
     * @see URL#getHost()
     */
    private String host;

    /**
     * For example:
     * {@code /Josef-Friedrich/javadoc-taglets/blob/066acfb1c11107be603580b9a603cf3c892e3c60/src/main/java/rocks/friedrich/permalink_taglet/PermalinkTaglet.java}
     *
     * @see URL#getHost()
     */
    private String path;

    /**
     * For example: {@code Josef-Friedrich}
     */
    private String owner;

    /**
     * For example: {@code javadoc-taglets}
     */
    private String repo;

    /**
     * For example: {@code 066acfb1c11107be603580b9a603cf3c892e3c60}
     */
    private String commitId;

    private String branch;

    private boolean raw;

    /**
     * For example:
     * {@code src/main/java/rocks/friedrich/permalink_taglet/PermalinkTaglet.java}
     */
    private String file;

    private LineRange lineRange;

    /**
     * Text that is displayed as the link text.
     */
    private String display;

    public Repolink(String tagText)
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
        host = this.url.getHost().toLowerCase();
        String[] segments = path.split("/");
        owner = segments[1];
        repo = segments[2];
        // Github
        // for example:
        // https://github.com/Josef-Friedrich/javadoc-taglets/blob/main/src/main/java/rocks/friedrich/permalink_taglet/PermalinkTaglet.java
        // https://github.com/junit-team/junit5-samples/blob/425c27ecd9cefee5a1459e4cc9efd1c8390836e3/junit5-jupiter-starter-maven/pom.xml#L9-L15
        if (host.equals("github.com") && segments.length > 5
                && segments[3].equals("blob"))
        {
            if (isSha1(segments[4]))
            {
                commitId = segments[4];
            }
            else
            {
                branch = segments[4];
            }
            file = joinSegments(segments, 5);
            // Forgejo
            // https://inf.pirckheimer-gymnasium.de/drupal/pgn_gallery/src/commit/25df4b141c3897a3671d494a4015ecaa00275b6d/pgn_gallery.module#L9-L21
            // https://inf.pirckheimer-gymnasium.de/engine-pi/engine-pi/src/branch/main/README.md
            // https://inf.pirckheimer-gymnasium.de/engine-pi/engine-pi/raw/branch/main/README.md
        }
        else if (segments.length > 5
                && (segments[3].equals("src") || segments[3].equals("raw")))
        {
            if (segments[3].equals("raw"))
            {
                raw = true;
            }
            if (segments[4].equals("branch"))
            {
                branch = segments[5];
            }
            else if (segments[4].equals("commit") && isSha1(segments[5]))
            {
                commitId = segments[5];
            }
            file = joinSegments(segments, 6);
        }
        // for example: #L9-L21
        String ref = this.url.getRef();
        if (ref != null)
        {
            lineRange = new LineRange(ref);
        }
    }

    private static boolean isSha1(String text)
    {
        Pattern pattern = Pattern.compile("[0-9a-f]{40}",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }

    private static String joinSegments(String[] segments, int fromIndex)
    {
        String[] subset = Arrays.copyOfRange(segments, fromIndex,
                segments.length);
        return String.join("/", subset);
    }

    /**
     * Get the complete URL of the repository link.
     *
     * @return The complete URL of the repository link.
     *
     * @see URL#toString()
     */
    public String getUrl()
    {
        return url.toString();
    }

    /**
     * Get the host name of the repository link URL.
     *
     * @return The host name of the repository link URL, for example:
     *         {@code github.com}.
     *
     * @see URL#getHost()
     */
    public String getHost()
    {
        return host;
    }

    public String getHostDisplay()
    {
        if (host.equals("github.com"))
        {
            return "Github: ";
        }
        return host;
    }

    /**
     * For example:
     * {@code /Josef-Friedrich/javadoc-taglets/blob/066acfb1c11107be603580b9a603cf3c892e3c60/src/main/java/rocks/friedrich/permalink_taglet/PermalinkTaglet.java}
     */
    public String getPath()
    {
        return path;
    }

    /**
     * @return For example: {@code Josef-Friedrich}
     */
    public String getOwner()
    {
        return owner;
    }

    /**
     * @return For example: {@code javadoc-taglets}
     */
    public String getRepo()
    {
        return repo;
    }

    /**
     * @return For example:
     *         {@code src/main/java/rocks/friedrich/permalink_taglet/PermalinkTaglet.java}
     */
    public String getFile()
    {
        return file;
    }

    public String getBranch()
    {
        return branch;
    }

    public boolean isRaw()
    {
        return raw;
    }

    /**
     * @return For example: {@code 066acfb1c11107be603580b9a603cf3c892e3c60}
     */
    public String getCommitId()
    {
        return commitId;
    }

    /**
     * @return For example: {@code 066acfb}
     */
    public String getShortCommitId()
    {
        return commitId.substring(0, 7);
    }

    public LineRange getLineRange()
    {
        return lineRange;
    }

    public String getDisplay()
    {
        if (display != null)
        {
            return display;
        }
        String output = String.format("%s%s/%s", getHostDisplay(), getOwner(),
                getRepo());
        if (getFile() != null)
        {
            output += " " + getFile();
        }
        if (getLineRange() != null)
        {
            output += " " + getLineRange();
        }
        return output;
    }

    public String generateHtmlLink()
    {
        return String.format("<a href=\"%s\">%s</a>", url.toString(),
                getDisplay());
    }
}
