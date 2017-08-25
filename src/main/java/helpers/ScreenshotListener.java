package helpers;

import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.testng.ScreenShooter;
import com.google.common.io.Files;
import org.testng.ITestResult;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;

public class ScreenshotListener extends ScreenShooter {

    @Attachment
    public static byte[] failureScreenshot() {
        try {
            File screenshot = Screenshots.takeScreenShotAsFile();

            return Files.toByteArray(screenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Error. Can't take screenshot".getBytes();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        failureScreenshot();
    }
}
