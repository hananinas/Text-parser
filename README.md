# BFST2023-handins

A JavaFX application for parsing danish addresses, built with Gradle.

> **Requirements:**


- Java 8 or later
- JavaFX 8 or later
- Gradle 6.0 or later

> **Installation**
To run the Address Parser with JavaFX and Gradle application, follow these steps:

- Clone or download the repository to your local machine.
- Open a terminal or command prompt and navigate to the project directory.
- Run the following command to build and run the application:

```
gradle run
```

> **Usage**

This part is still under development:

- The application consists of a user interface for entering an address, and a parsed result is displayed in a separate text area. You can also specify the format for the parsed address, such as "street, city".


The parsed address is obtained using the Parser class. In the event that the address cannot be parsed, a NoMatchFoundException is thrown.

The application follows a Model-View-Controller (MVC) design pattern, with the Address class representing the model, the JavaFX UI components representing the view, and the MainController class serving as the controller.
