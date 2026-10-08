# UTC Login UI Tests (Java + Selenium)

Java Selenium project for automating the UTC Electronic Office login page with JUnit 5, Maven, and the Page Object Model.

## Requirements

- Java 21
- Maven 3.9+
- Google Chrome

Selenium Manager automatically resolves the matching ChromeDriver when `ChromeDriver` is created. Do not download or add a `chromedriver.exe` to this project.

## Run tests

```powershell
mvn test
```

Chrome is configured to run headless by default.
