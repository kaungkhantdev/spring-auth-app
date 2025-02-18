# Basic Maven Commands for Spring Boot
## Clean the project:
Removes the target directory where compiled files are stored.

```bash 
mvn clean
```
## Compile the project:
Compiles the source code of the project.

```bash
mvn compile
```
## Run tests:
Executes all unit tests.

```bash 
mvn test
```
## Package the project:
Packages the compiled code into a .jar or .war file inside the target folder.

```bash
mvn package
```
## Run the Spring Boot Application:
Starts the Spring Boot application directly from the command line.

```bash
mvn spring-boot:run
```
## Install the package locally:
Compiles, tests, and installs the package into your local Maven repository (~/.m2/repository).

```bash
mvn install
```
## Skip tests during build:
Speeds up the build process by skipping tests.

```bash
mvn clean install -DskipTests
```
## Run a specific test class:
Runs a specific test class.

```bash
mvn -Dtest=ClassNameTest test
```
## Generate project documentation (site):
Builds a documentation site for the project.

```bash 
mvn site
```
## Update project dependencies:
Forces Maven to update all dependencies from remote repositories.

```bash
mvn clean install -U
```