package rocks.friedrich.permalink_taglet;

import java.util.List;
import java.util.Set;

import javax.lang.model.element.Element;

import com.sun.source.doctree.DocTree;
import com.sun.source.doctree.UnknownBlockTagTree;

import jdk.javadoc.doclet.Taglet;
import static jdk.javadoc.doclet.Taglet.Location.*;

/**
 *
 * @permalink https://github.com/Josef-Friedrich/permalink-javadoc-taglet/blob/e5bfad79e3544aeb80f2b8d761241011123e8325/src/main/java/rocks/friedrich/permalink_taglet/PermalinkTaglet.java#L17
 *            Link text
 */
public class PermalinkTaglet implements Taglet
{
    private static final String NAME = "permalink";

    private String genLink(String text)
    {
        String url = text;
        String display = text;
        int firstSpace = text.indexOf(' ');
        if (firstSpace != -1)
        {
            url = text.substring(0, firstSpace);
            display = text.substring(firstSpace).trim();
        }
        return String.format("<a href='%s'>%s</a>", url, display);
    }

    @Override
    public String getName()
    {
        return NAME;
    }

    @Override
    public Set<Location> getAllowedLocations()
    {
        return Set.of(OVERVIEW, MODULE, PACKAGE, TYPE, CONSTRUCTOR, METHOD,
                FIELD);
    }

    /**
     * @permalink https://github.com/Josef-Friedrich/permalink-javadoc-taglet/blob/e5bfad79e3544aeb80f2b8d761241011123e8325/src/main/java/rocks/friedrich/permalink_taglet/PermalinkTaglet.java#L17
     * @permalink https://github.com/Josef-Friedrich/permalink-javadoc-taglet/blob/e5bfad79e3544aeb80f2b8d761241011123e8325/src/main/java/rocks/friedrich/permalink_taglet/PermalinkTaglet.java#L17
     */
    @Override
    public boolean isInlineTag()
    {
        return false;
    }

    /**
     * @permalink https://github.com/Josef-Friedrich/permalink-javadoc-taglet/blob/e5bfad79e3544aeb80f2b8d761241011123e8325/src/main/java/rocks/friedrich/permalink_taglet/PermalinkTaglet.java#L17
     */
    @Override
    public String toString(List<? extends DocTree> tags, Element element)
    {
        if (tags.isEmpty())
        {
            return null;
        }
        StringBuilder buf = new StringBuilder(
                "<dl class=\"notes\"><dt>Permalink:</dt>");
        for (DocTree tag : tags)
        {
            String text = ((UnknownBlockTagTree) tag).getContent().get(0)
                    .toString();
            buf.append("<dd>").append(genLink(text)).append("</dd>");
        }
        return buf.toString();
    }
}
