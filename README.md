[![Maven Central](https://img.shields.io/maven-central/v/rocks.friedrich/permalink-taglet.svg?style=flat)](https://central.sonatype.com/artifact/rocks.friedrich/permalink-taglet)
[![javadoc](https://javadoc.io/badge2/rocks.friedrich/permalink-taglet/javadoc.svg)](https://javadoc.io/doc/rocks.friedrich/permalink-taglet)

# javadoc-taglets

This package provides following Javadoc tags:

* `@repolink`: Display formatted Github (or similar platforms) permalinks.
* `@drawio`: Embed DrawIO diagrams.
* `@youtube`: Embed YouTube videos.

[How to include this package in the javadoc configuration](https://maven.apache.org/plugins/maven-javadoc-plugin/examples/taglet-configuration.html)

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-javadoc-plugin</artifactId>
    <version>3.12.0</version>
    <configuration>

    </configuration>
</plugin>
```

```xml
<configuration>
    <taglet>rocks.friedrich.taglets.RepolinkTaglet</taglet>
    <tagletArtifact>
        <groupId>rocks.friedrich</groupId>
        <artifactId>taglets</artifactId>
        <version>0.4.0</version>
    </tagletArtifact>
</configuration>
```

```xml
<configuration>
    <!-- to disable the "missing" warnings -->
    <doclint>all,-missing</doclint>
    <taglets>
        <taglet>
            <tagletClass>rocks.friedrich.taglets.DrawioTaglet</tagletClass>
        </taglet>
        <taglet>
            <tagletClass>rocks.friedrich.taglets.RepolinkTaglet</tagletClass>
        </taglet>
        <taglet>
            <tagletClass>rocks.friedrich.taglets.YoutubeTaglet</tagletClass>
        </taglet>
    </taglets>
    <tagletArtifact>
        <groupId>rocks.friedrich</groupId>
        <artifactId>taglets</artifactId>
        <version>0.4.0</version>
    </tagletArtifact>
</configuration>
```
