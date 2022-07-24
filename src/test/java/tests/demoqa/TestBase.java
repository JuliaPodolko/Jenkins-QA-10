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
   /*     CredentialsConfig credentialsConfig = ConfigFactory.create(CredentialsConfig.class);

     //   Configuration.baseUrl = "https://demoqa.com";
     //   Configuration.browserSize = "1920x1080";
       // Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        String browserSize = System.getProperty("browserSize");
        String browser = System.getProperty("browser");
        String baseUrl = System.getProperty("baseUrl");
        String selenoidLogin = credentialsConfig.login();
        String selenoidPassword = credentialsConfig.password();
    //    String https= "https://";
    //    String credentials = config.login() + ":" + config.password();
        String selenoidUrl = System.getProperty("selenoidUrl");

    //    Configuration.remote = https + credentials + selenoidUrl;
        String selenoidConnectionString = String.format("https://%s:%s@%s/wd/hub",
                selenoidLogin,
                selenoidPassword,
                selenoidUrl);
        System.out.println(selenoidUrl);
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Configuration.baseUrl = baseUrl;
        Configuration.browserSize = browserSize;
        Configuration.browser = browser;
        Configuration.remote = selenoidConnectionString; */

        CredentialsConfig credentialConfig = ConfigFactory.create(CredentialsConfig.class);
        String selenoidLogin = credentialConfig.login();
        String selenoidPassword = credentialConfig.password();

        String selenoidURL = System.getProperty("selenoidURL", "selenoid.autotests.cloud");
        System.out.println(selenoidURL);
        String selenoidConnectionString = String.format("https://%s:%s@%s/wd/hub",
                selenoidLogin,
                selenoidPassword,
                selenoidURL);
        SelenideLogger.addListener("allure", new AllureSelenide());
    //    String browserType = System.getProperty("browserType", "chrome");
        String browserSize = System.getProperty("browserSize", "1920x1080");

        Configuration.remote = selenoidConnectionString;
        Configuration.baseUrl = "https://demoqa.com";
     //   Configuration.browserSize = "1920x1080";
    //    Configuration.browser = browserType;
        Configuration.browserSize = browserSize;

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
