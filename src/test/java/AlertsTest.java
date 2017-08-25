import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import ru.yandex.qatools.allure.annotations.Description;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.TestCaseId;

import static com.codeborne.selenide.Selenide.$;

public class AlertsTest extends TestBase {
    private static final By CLICK_FOR_JS_ALERT_BUTTON = By.xpath("//button[@onclick=\"jsAlert()\"]");
    private static final By CLICK_FOR_JS_CONFIRM_BUTTON = By.xpath("//button[@onclick=\"jsConfirm()\"]");
    private static final By CLICK_FOR_JS_PROMPT_BUTTON = By.xpath("//button[@onclick=\"jsPrompt()\"]");
    private static final By RESULT_MESSAGE = By.id("result");
    private static final String SITE_URL = "https://the-internet.herokuapp.com/javascript_alerts";
    private static final String ALERT_PROMPT_TEXT = "Hello world!";

    public AlertsTest() {
        super(SITE_URL);
    }

    @Features("Alerts")
    @Description("JS alert test")
    @Test
    @TestCaseId("ID-1")
    public void clickJSAlerts() {
        $(CLICK_FOR_JS_ALERT_BUTTON).click();
        Selenide.confirm("I am a JS Alert");
        $(RESULT_MESSAGE).shouldHave(Condition.text("You successfuly clicked an alert"));
    }

    @Features("Alerts")
    @Description("JS confirm test")
    @Test
    @TestCaseId("ID-2")
    public void clickJSConfirm() {
        $(CLICK_FOR_JS_CONFIRM_BUTTON).click();
        Selenide.confirm("I am a JS Confirm");
        $(RESULT_MESSAGE).shouldHave(Condition.text("You clicked: Ok"));
    }

    @Features("Alerts")
    @Description("JS Prompt test")
    @Test
    @TestCaseId("ID-3")
    public void clickJSPrompt() {
        $(CLICK_FOR_JS_PROMPT_BUTTON).click();
        Selenide.switchTo().alert().sendKeys(ALERT_PROMPT_TEXT);
        Selenide.confirm("I am a JS prompt");
        $(RESULT_MESSAGE).shouldHave(Condition.text("You entered: " + ALERT_PROMPT_TEXT));
    }
}
