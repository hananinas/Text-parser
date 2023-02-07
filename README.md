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

> This procces is done using the following regular expression:

```
^(?<street>[\D\s.]+)\s+(?<number>\d+\D?),\s+((?<floor>st\.|kl\.|[\d.]{0,2})\s?(?<side>th|tv|mf|\D\d{1,2}))?(?<additionalCity>[^,]+)?(, )?(?<postcode>\d{4})\s*(?<city>\D+)$
```

- This regular expression is used to match strings that represent an address, which includes the following parts:

- Street: the street name, captured as the "street" group. Matched by the pattern [\D\s.]+. This pattern matches one or more characters that are not a digit, whitespace, or a dot.

- Number: the house number, captured as the "house" group. Matched by the pattern \d+\D?, which matches one or more digits followed by an optional non-digit character.

- Floor: the floor of the building, captured as the "floor" group. Matched by the pattern st\.|kl\.|\d{0,2}. This pattern matches either the string "st." or "kl.", or zero to two characters that are digits or dots.

- Side: the side of the building, captured as the "side" group. Matched by the pattern th|tv|mf|\D\d{1,2}. This pattern matches either the string "th", "tv", or "mf", or one or two characters that are not a digit.

- AdditionalCity: additional information about the city, captured as the "additionalCity" group. Matched by the pattern [^,]+?, which matches one or more characters that are not a comma.

- Postcode: the postcode, captured as the "postcode" group. Matched by the pattern \d{4}, which matches exactly four digits.

- City: the city name, captured as the "city" group. Matched by the pattern \D+. This pattern matches one or more characters that are not a digit.

- The overall string is matched by the pattern \s*, which matches zero or more whitespaces.



The application follows a Model-View-Controller (MVC) design pattern, with the Address class representing the model, the JavaFX UI components representing the view, and the MainController class serving as the controller.

