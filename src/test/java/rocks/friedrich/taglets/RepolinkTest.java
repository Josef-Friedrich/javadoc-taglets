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

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class RepolinkTest
{
    @Test
    void testGithubBeginAndEndLines()
    {
        Repolink link = new Repolink(
                "https://github.com/junit-team/junit5-samples/blob/425c27ecd9cefee5a1459e4cc9efd1c8390836e3/junit5-jupiter-starter-maven/pom.xml#L9-L15");
        assertEquals(link.getUrl(),
                "https://github.com/junit-team/junit5-samples/blob/425c27ecd9cefee5a1459e4cc9efd1c8390836e3/junit5-jupiter-starter-maven/pom.xml#L9-L15");
        assertEquals(link.getHost(), "github.com");
        assertEquals(link.getPath(),
                "/junit-team/junit5-samples/blob/425c27ecd9cefee5a1459e4cc9efd1c8390836e3/junit5-jupiter-starter-maven/pom.xml");
        assertEquals(link.getOwner(), "junit-team");
        assertEquals(link.getRepo(), "junit5-samples");
        assertEquals(link.getCommitId(),
                "425c27ecd9cefee5a1459e4cc9efd1c8390836e3");
        assertEquals(link.getFile(), "junit5-jupiter-starter-maven/pom.xml");
        assertEquals(link.getLineRange().getBegin(), 9);
        assertEquals(link.getLineRange().getEnd(), 15);
    }

    @Test
    void testGithubBeginOnlyLine()
    {
        Repolink link = new Repolink(
                "https://github.com/junit-team/junit5-samples/blob/425c27ecd9cefee5a1459e4cc9efd1c8390836e3/junit5-jupiter-starter-maven/pom.xml#L7");
        assertEquals(link.getLineRange().getBegin(), 7);
        assertEquals(link.getLineRange().getEnd(), -1);
    }

    @Nested
    class ForgejoTest
    {
        @Test
        void testPermlinkWithCommitId()
        {
            Repolink link = new Repolink(
                    "https://inf.pirckheimer-gymnasium.de/drupal/pgn_gallery/src/commit/25df4b141c3897a3671d494a4015ecaa00275b6d/pgn_gallery.module#L9-L21");
            assertEquals(link.getLineRange().getBegin(), 9);
            assertEquals(link.getLineRange().getEnd(), 21);
        }

        @Test
        void testBranch()
        {
            new Repolink(
                    "https://inf.pirckheimer-gymnasium.de/engine-pi/engine-pi/src/branch/main/README.md");
        }

        @Test
        void testRaw()
        {
            new Repolink(
                    "https://inf.pirckheimer-gymnasium.de/engine-pi/engine-pi/raw/branch/main/README.md");
        }

        @Test
        void testQuery()
        {
            new Repolink(
                    "https://inf.pirckheimer-gymnasium.de/engine-pi/engine-pi/src/branch/main/README.md?display=source");
        }
    }

    class GitlabTest
    {
        @Test
        void testRepoRootTwoLevels()
        {
            new Repolink("https://gitlab.com/rak-n-rok/krake");
        }

        @Test
        void testRepoRootThreeLevels()
        {
            new Repolink("https://gitlab.com/liscioapps/fun/collar-tug");
        }
    }

    @Test
    void testGitlab()
    {
        Repolink link = new Repolink(
                "https://git.drupalcode.org/sandbox/joseffriedrich-2058951/-/blob/b4502dd4ae0f5bf84ce9e46fecfcbd3e152526b8/pgn_access.module#L33-52");
        assertEquals(link.getLineRange().getBegin(), 33);
        assertEquals(link.getLineRange().getEnd(), 52);
    }

    @Nested
    class AttributesTest
    {
        Repolink link = new Repolink(
                "https://github.com/Josef-Friedrich/javadoc-taglets/blob/066acfb1c11107be603580b9a603cf3c892e3c60/src/main/java/rocks/friedrich/permalink_taglet/PermalinkTaglet.java#L23-L42");

        @Test
        void testGetUrl()
        {
            assertEquals(link.getUrl().toString(),
                    "https://github.com/Josef-Friedrich/javadoc-taglets/blob/066acfb1c11107be603580b9a603cf3c892e3c60/src/main/java/rocks/friedrich/permalink_taglet/PermalinkTaglet.java#L23-L42");
        }

        @Test
        void testGetDisplay()
        {
            assertEquals(link.getDisplay(),
                    "github.com/Josef-Friedrich/javadoc-taglets src/main/java/rocks/friedrich/permalink_taglet/PermalinkTaglet.java Lines 23 - 42");
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
            assertEquals(link.getRepo(), "javadoc-taglets");
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
