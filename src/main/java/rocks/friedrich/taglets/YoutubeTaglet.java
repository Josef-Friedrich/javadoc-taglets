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

import static jdk.javadoc.doclet.Taglet.Location.CONSTRUCTOR;
import static jdk.javadoc.doclet.Taglet.Location.FIELD;
import static jdk.javadoc.doclet.Taglet.Location.METHOD;
import static jdk.javadoc.doclet.Taglet.Location.MODULE;
import static jdk.javadoc.doclet.Taglet.Location.OVERVIEW;
import static jdk.javadoc.doclet.Taglet.Location.PACKAGE;
import static jdk.javadoc.doclet.Taglet.Location.TYPE;

import java.util.List;
import java.util.Set;

import javax.lang.model.element.Element;

import com.sun.source.doctree.DocTree;

import jdk.javadoc.doclet.Taglet;

/**
 * @author Josef Friedrich
 *
 * @since 0.4.0
 */
public class YoutubeTaglet implements Taglet
{
    private static final String NAME = "youtube";

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
        return true;
    }

    @Override
    public String toString(List<? extends DocTree> tags, Element element)
    {
        return "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube-nocookie.com/embed/F0OkwXKcPSE?si=t_7pcVPmK6XW8r4g\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
    }
}
