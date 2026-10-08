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

## TC1: Valid login

TC1 requires a real UTC account. In Windows PowerShell, set credentials only in the current shell:

```powershell
$env:UTC_USER="your_username"
$env:UTC_PASS="your_password"
```

Credentials are read from environment variables and must not be committed. If either variable is missing, JUnit reports TC1 as skipped (BLOCKED), not passed. TC1 checks that a successful login navigates away from `/Login`.
