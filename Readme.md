# Spring Storage Module

This module is manage the file saving logic. The module just manage the file save you are
responsable to do the relationship with another objects.

## Submodules

The project is splitted on three submodules:

 - [Domain](src/domain/Readme.md)
 - [Service](src/service/Readme.md)
 - [Web](src/web/Readme.md)

## Configuration

The only property needed to works is the path to save the files on local directory.

| Property                                              | Description           |
| ----------------------------------------------------- | --------------------- |
| `com.thelastimperial.storage.service.storage.path`    | Path to save files.   |

