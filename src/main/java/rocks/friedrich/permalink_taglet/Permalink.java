package rocks.friedrich.permalink_taglet;

import java.net.MalformedURLException;
import java.net.URL;

public class Permalink
{
    String url;

    String linkText;

    public Permalink(String tagText) throws MalformedURLException
    {
        URL url = new URL(tagText);
    }
}
