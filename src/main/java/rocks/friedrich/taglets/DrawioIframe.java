package rocks.friedrich.taglets;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Objects.requireNonNull;

import java.net.URLEncoder;

public class DrawioIframe
{
    private String url;

    public DrawioIframe(String url)
    {
        this.url = requireNonNull(url, "url must not be null");
    }

    private String encodeUrl()
    {
        return URLEncoder.encode(url, UTF_8);
    }

    public String toString()
    {
        return "<iframe frameborder=\"0\" src=\"https://app.diagrams.net/?lightbox=1&edit=_blank#U"
                + encodeUrl() + "\"></iframe>";
    }
}
