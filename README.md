# XenforoResourceManagerAPI-Java

[![Maven Central](https://img.shields.io/maven-central/v/de.jonahd345/xenfororesourcemanagerapi)](https://central.sonatype.com/artifact/de.jonahd345/xenfororesourcemanagerapi)
[![License](https://img.shields.io/github/license/JonaHD345/XenforoResourceManagerAPI-Java)](LICENSE)

Java-client for the XenforoResourceManagerAPI from SpigotMC.org https://github.com/SpigotMC/XenforoResourceManagerAPI

Issues and pull requests are welcome!

## Installation

To use this project in your Maven-based project, add the following dependency:

```xml
<dependency>
    <groupId>de.jonahd345</groupId>
    <artifactId>xenfororesourcemanagerapi</artifactId>
    <version>1.1.1</version>
</dependency>
```
## Usage

```java
XenforoResourceManagerAPI api = new XenforoResourceManagerAPI();

// get Resource (with id)
Resource resource = api.getResource(106888);
if (resource != null) {
    List<Update> updatesOfResource = api.getResourceUpdates(resource.getId());
}

// get Author async (in this way available for all endpoints)
CompletableFuture<Author> futureAuthor = api.getAuthorAsync(1407849);

futureAuthor.thenAccept(author -> {
    if (author != null) {
        System.out.println("Author: " + author.getUsername());
    } else {
        System.out.println("Author not found.");
    }
});
```

<br>
coded with ❤️ by JonaHD345
