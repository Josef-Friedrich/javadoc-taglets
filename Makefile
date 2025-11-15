test:
	mvn clean
	mvn test

install:
	mvn install

package:
	mvn package

doc: clean install
	rm -rf target/reports/apidocs
	mvn javadoc:javadoc
	xdg-open target/reports/apidocs/index.html

docs: doc

clean:
	mvn clean

format:
	mvn formatter:format

.PHONY: install mvn_install_home mvn_install_local_repo package test format
