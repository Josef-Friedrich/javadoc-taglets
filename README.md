[![Maven Central](https://img.shields.io/maven-central/v/rocks.friedrich/permalink-taglet.svg?style=flat)](https://central.sonatype.com/artifact/rocks.friedrich/permalink-taglet)
[![javadoc](https://javadoc.io/badge2/rocks.friedrich/permalink-taglet/javadoc.svg)](https://javadoc.io/doc/rocks.friedrich/permalink-taglet)

# javadoc-taglets

Provides a @permalink tag for Javadoc to display formatted Github (or similar platforms) permalinks.

```xml

<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-javadoc-plugin</artifactId>
    <version>3.8.0</version>
    <configuration>

        <!--
        https://maven.apache.org/plugins/maven-javadoc-plugin/examples/taglet-configuration.html -->
        <taglet>rocks.friedrich.permalink_taglet.PermalinkTaglet</taglet>

        <tagletArtifact>
            <groupId>rocks.friedrich</groupId>
            <artifactId>permalink-taglet</artifactId>
            <version>0.1.0</version>
        </tagletArtifact>

    </configuration>

    <executions>
        <execution>
            <id>attach-javadocs</id>
            <goals>
                <goal>jar</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```
