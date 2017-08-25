import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import ru.yandex.qatools.allure.annotations.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.TestCaseId;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class IFrameTest extends TestBase {
    private static final By YOUR_CONTENT_GOES_HERE = By.cssSelector("#tinymce");
    private static final By BOLD_BUTTON = By.xpath("//div[@id='mceu_3']");
    private static final By STRONG_LABEL = By.xpath(".//*[@id='mceu_29']//div[contains(text(), \"strong\")]");
    private static String siteURL = "https://the-internet.herokuapp.com/iframe";

    public IFrameTest() {
        super(siteURL);
    }

    @Features("Iframes")
    @Description("Clear the text from the field")
    @Test
    @TestCaseId("ID-5")
    public void clearText() {
        open(siteURL);
        Selenide.switchTo().frame("mce_0_ifr");
        $(YOUR_CONTENT_GOES_HERE).
                click();
        Selenide.actions().sendKeys().
                sendKeys(Keys.CONTROL).
                sendKeys(Keys.chord("A")).
                sendKeys(Keys.BACK_SPACE).
                build().perform();
        Assert.assertTrue($(YOUR_CONTENT_GOES_HERE).is(Condition.empty), "the text field is not empty");
    }

    @Features("Iframes")
    @Description("Enter the text in the field")
    @Test(dependsOnMethods = "clearText")
    @TestCaseId("ID-6")
    public void enterText() {
        $(YOUR_CONTENT_GOES_HERE).sendKeys("Hello world!");
        Selenide.switchTo().defaultContent();
        Selenide.actions().
                sendKeys(Keys.CONTROL).
                sendKeys(Keys.ARROW_LEFT).
                sendKeys(Keys.CONTROL).
                sendKeys(Keys.ARROW_RIGHT).
                build().perform();

        $(BOLD_BUTTON).click();
        Selenide.actions().sendKeys(Keys.END). //select the "world!" text by the steps below to verify the text is displayed in bold
                sendKeys(Keys.SHIFT).
                sendKeys(Keys.CONTROL).
                sendKeys(Keys.LEFT).
                build().perform();
        Assert.assertTrue($(STRONG_LABEL).is(Condition.visible));
    }
}
