package rocks.friedrich.permalink_taglet;

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
}
