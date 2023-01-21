package baseEntities;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import core.ReadProperties;
import io.qameta.allure.selenide.AllureSelenide;
import models.TestCase;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {

    protected TestCase testCaseBuilder;
    protected TestCase testCase2Builder;

    @BeforeSuite
    public static void setupAllureReports() {

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false)
        );

        //setup logger
        org.apache.log4j.BasicConfigurator.configure();
    }

    @BeforeClass
    public void prepareModelsData() {

        testCaseBuilder = TestCase.builder()
                .title(RandomStringUtils.randomAlphanumeric(15))
                .status("Actual")
                .description(RandomStringUtils.randomAlphabetic(10))
                .suite("Test cases without suite")
                .severity("Critical")
                .priority("High")
                .type("Smoke")
                .layer("Unit")
                .isFlaky("No")
                .milestone("Not set")
                .behavior("Not set")
                .automationStatus("Not automated")
                .preconditions(RandomStringUtils.randomAlphabetic(50))
                .postconditions(RandomStringUtils.randomAlphanumeric(50))
                .build();

        testCase2Builder = TestCase.builder()
                .layer("API")
                .preconditions(RandomStringUtils.randomAlphanumeric(15))
                .build();
    }

    @BeforeClass
    public void setup() {

        clearBrowserCookies();
        Configuration.baseUrl = ReadProperties.getInstance().getURL();
        Configuration.browser = ReadProperties.getInstance().getBrowserName();
        Configuration.browserSize = ReadProperties.getInstance().getBrowserSize();
        Configuration.headless = ReadProperties.getInstance().isHeadless();
        Configuration.pageLoadTimeout = ReadProperties.getInstance().getPageLoadTimeout();
    }

    @AfterClass
    public void tearDown() {

        clearBrowserCookies();
        closeWebDriver();
    }
}
