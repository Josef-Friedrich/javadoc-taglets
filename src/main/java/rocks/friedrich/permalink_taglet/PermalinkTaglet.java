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
package rocks.friedrich.permalink_taglet;

import java.util.List;
import java.util.Set;

import javax.lang.model.element.Element;

import com.sun.source.doctree.DocTree;
import com.sun.source.doctree.UnknownBlockTagTree;

import jdk.javadoc.doclet.Taglet;
import static jdk.javadoc.doclet.Taglet.Location.*;

/**
 * The PermalinkTaglet class implements the Taglet interface and represents a
 * custom Javadoc tag called "@permalink". This tag is used to generate
 * permalinks to specific lines of code in a GitHub repository.
 *
 * Example usage:
 *
 * {@code
 * /**
 *  * This is a sample class.
 *  * @permalink https://github.com/example/repository/blob/commit-hash/path/to/file.java#L10-L20
 * *\/ public class SampleClass { // class implementation } }
 *
 * @author Josef Friedrich
 */
public class PermalinkTaglet implements Taglet
{
    private static final String NAME = "permalink";

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

    @Override
    public boolean isInlineTag()
    {
        return false;
    }

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
            buf.append("<dd>").append(new Permalink(text).generateHtmlLink())
                    .append("</dd>");
        }
        return buf.toString();
    }
}
