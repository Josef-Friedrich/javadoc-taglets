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

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;

class PermalinkTest
{
    @Test
    void testGithubBeginAndEndLines() throws MalformedURLException
    {
        Permalink link = new Permalink(
                "https://github.com/junit-team/junit5-samples/blob/425c27ecd9cefee5a1459e4cc9efd1c8390836e3/junit5-jupiter-starter-maven/pom.xml#L9-L15");
        assertEquals(link.getLineRange().getBegin(), 9);
        assertEquals(link.getLineRange().getEnd(), 15);
        assertEquals(link.getCommitId(),
                "425c27ecd9cefee5a1459e4cc9efd1c8390836e3");
        assertEquals(link.getFile(), "junit5-jupiter-starter-maven/pom.xml");
        assertEquals(link.getOwner(), "junit-team");
        assertEquals(link.getRepo(), "junit5-samples");
    }

    @Test
    void testGithubBeginOnlyLine() throws MalformedURLException
    {
        Permalink link = new Permalink(
                "https://github.com/junit-team/junit5-samples/blob/425c27ecd9cefee5a1459e4cc9efd1c8390836e3/junit5-jupiter-starter-maven/pom.xml#L7");
        assertEquals(link.getLineRange().getBegin(), 7);
        assertEquals(link.getLineRange().getEnd(), -1);
    }

    @Test
    void testForgejo() throws MalformedURLException
    {
        Permalink link = new Permalink(
                "https://inf.pirckheimer-gymnasium.de/drupal/pgn_gallery/src/commit/25df4b141c3897a3671d494a4015ecaa00275b6d/pgn_gallery.module#L9-L21");
        assertEquals(link.getLineRange().getBegin(), 9);
        assertEquals(link.getLineRange().getEnd(), 21);
    }

    @Test
    void testGitlab() throws MalformedURLException
    {
        Permalink link = new Permalink(
                "https://git.drupalcode.org/sandbox/joseffriedrich-2058951/-/blob/b4502dd4ae0f5bf84ce9e46fecfcbd3e152526b8/pgn_access.module#L33-52");
        assertEquals(link.getLineRange().getBegin(), 33);
        assertEquals(link.getLineRange().getEnd(), 52);
    }

    @Nested
    class AttributesTest
    {
        Permalink link = new Permalink(
                "https://github.com/Josef-Friedrich/permalink-javadoc-taglet/blob/066acfb1c11107be603580b9a603cf3c892e3c60/src/main/java/rocks/friedrich/permalink_taglet/PermalinkTaglet.java#L23-L42");

        @Test
        void testGetUrl()
        {
            assertEquals(link.getUrl().toString(),
                    "https://github.com/Josef-Friedrich/permalink-javadoc-taglet/blob/066acfb1c11107be603580b9a603cf3c892e3c60/src/main/java/rocks/friedrich/permalink_taglet/PermalinkTaglet.java#L23-L42");
        }

        @Test
        void testGetDisplay()
        {
            assertEquals(link.getDisplay(),
                    "github.com/Josef-Friedrich/permalink-javadoc-taglet src/main/java/rocks/friedrich/permalink_taglet/PermalinkTaglet.java Lines 23 - 42");
        }

        @Test
        void testGetLineRange()
        {
            assertEquals(link.getLineRange().getBegin(), 23);
            assertEquals(link.getLineRange().getEnd(), 42);
        }

        @Test
        void testGetHost()
        {
            assertEquals(link.getHost(), "github.com");
        }

        @Test
        void testGetOwner()
        {
            assertEquals(link.getOwner(), "Josef-Friedrich");
        }

        @Test
        void testGetRepo()
        {
            assertEquals(link.getRepo(), "permalink-javadoc-taglet");
        }

        @Test
        void testGetFile()
        {
            assertEquals(link.getFile(),
                    "src/main/java/rocks/friedrich/permalink_taglet/PermalinkTaglet.java");
        }

        @Test
        void testGetCommitId()
        {
            assertEquals(link.getCommitId(),
                    "066acfb1c11107be603580b9a603cf3c892e3c60");
        }

        @Test
        void testGetShortCommitId()
        {
            assertEquals(link.getShortCommitId(), "066acfb");
        }
    }
}
