### Overview
Automation project combining UI tests (Selenide + TestNG) and API tests (Rest Assured + TestNG) with Allure reporting. The suite targets Qase (`https://app.qase.io/` and `https://api.qase.io/`) and demonstrates typical flows: project and suite CRUD via API and end‑to‑end UI flows for login, project creation, and test case operations.

### Tech stack
- **Build**: Maven (Java 8)
- **Test framework**: TestNG
- **UI**: Selenide (Chrome by default)
- **API**: Rest Assured (with JSON schema validation)
- **Reporting**: Allure (TestNG + Selenide integrations)
- **Logging**: SLF4J + Log4j
- **Boilerplate reduction**: Lombok

### Project structure
- `src/test/java/`
  - `tests/ui` – UI TestNG tests
  - `tests/api` – API TestNG tests
  - `tests/apiUi` – mixed API+UI flow(s)
  - `pages` – Page Object Model for UI
  - `steps` – high‑level actions/steps used by tests
  - `adapters` – API adapters/clients
  - `endpoints` – API and UI endpoints/constants
  - `models` – POJOs for request/response models
  - `baseEntities` – base test/page/api classes and utilities
- `src/test/resources/`
  - `config.properties` – execution/configuration settings
  - `testng.xml` – suite configuration listing UI and API test classes
  - `allure.properties`, `log4j.xml` – reporting/logging configs
  - `files/` – sample upload/import files used by tests

### Prerequisites
- Java 8 (1.8)
- Maven 3.6+
- Chrome browser installed (for Selenide default)

### Configuration
Main settings live in `src/test/resources/config.properties`:
- **UI**
  - `browser=chrome` – target browser
  - `isHeadless=false` – headless mode toggle
  - `baseUrl=https://app.qase.io/` – base URL
  - `timeout`, `pageLoadTimeout`, `browserSize` – timeouts and viewport
- **Credentials**
  - `userName`, `password` – UI login credentials
- **API**
  - `api_url=https://api.qase.io/`
  - `api_key=<your_api_token>` – API token

Security note:
- Do not commit real credentials or API tokens. Prefer environment‑specific copies of `config.properties` or CI‑level secret injection.

### How to run
From the project root:
1) Run the full TestNG suite (uses `src/test/resources/testng.xml`):
```bash
mvn clean test
```
If Maven doesn’t automatically pick up the suite file, force it:
```bash
mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testng.xml
```

2) Run a specific test class:
```bash
mvn -Dtest=tests.ui.ProjectCreateCorrectName_BoundaryTest test
```

3) Override selected properties at runtime (example: headless Chrome):
```bash
mvn clean test -DisHeadless=true -Dbrowser=chrome
```

### Allure reporting
Test results are written to `target/allure-results`.

- Serve an interactive report locally:
```bash
mvn allure:serve
```

- Generate a static report (HTML in `target/site/allure-maven-plugin`):
```bash
mvn allure:report
```

### Useful locations
- `target/allure-results/` – raw Allure results
- `target/test-classes/` – compiled test classes and copied resources
- `src/test/resources/files/` – sample data (e.g., `testrail.csv`, `squashtm.xls`)

### Notes
- Test selection is controlled via `testng.xml` (UI and API suites are listed as separate `<test>` blocks).
- Selenide defaults to Chrome; switch browsers using `browser` in `config.properties` or a system property.
- API client code lives under `adapters` and `endpoints/api`, with models under `models/*`.

### Troubleshooting
- If browser does not start on macOS CI/headless: set `-DisHeadless=true` and ensure Chrome/Chromedriver compatibility.
- If Allure report is empty: ensure tests ran and `target/allure-results` contains files before invoking `allure:serve`.
- If credentials/API key are invalid: update `config.properties` or pass via environment/CI secrets.

