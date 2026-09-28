# Service module

This module have the logic to save the file data and the table that represent it.

Here are two main interfaces `StorageService` and `StorageBlobService` that are handle
by `StorageHandler` class. The default implementation save the tables with the JPA and
the file in local directory.

## Instalation

```xml
<dependency>
  <groupId>com.thelastimperial.storage</groupId>
  <artifactId>service</artifactId>
  <version>0.0.1</version>
</dependency>
```
