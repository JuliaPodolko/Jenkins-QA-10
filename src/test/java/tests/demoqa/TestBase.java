package tests.demoqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.CredentialsConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    @BeforeAll
    static void setUP () {
        CredentialsConfig credentialsConfig = ConfigFactory.create(CredentialsConfig.class);

        String browserSize = System.getProperty("browserSize", "1920x1080");
        String browser = System.getProperty("browser", "chrome");
        String baseUrl = System.getProperty("baseUrl", "https://demoqa.com");
        String selenoidLogin = credentialsConfig.login();
        String selenoidPassword = credentialsConfig.password();
        String selenoidUrl = System.getProperty("selenoidUrl", "selenoid.autotests.cloud");
        String selenoidConnectionString = String.format("https://%s:%s@%s/wd/hub",
                selenoidLogin,
                selenoidPassword,
                selenoidUrl);
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Configuration.baseUrl = baseUrl;
        Configuration.browserSize = browserSize;
        Configuration.browser = browser;
        Configuration.remote = selenoidConnectionString;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }
    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last Screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}
