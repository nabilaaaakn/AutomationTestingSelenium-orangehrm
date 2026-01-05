import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TC_003_03 {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        // Login
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")))
                .sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Admin → Users
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Admin']"))).click();

        // ===== FILTER STATUS = ENABLED =====
        By statusDropdown = By.xpath(
                "//label[text()='Status']/following::div[contains(@class,'oxd-select-text')][1]"
        );

        wait.until(ExpectedConditions.elementToBeClickable(statusDropdown)).click();

// tunggu & klik option Enabled
        By enabledOption = By.xpath(
                "//div[@role='option' and normalize-space()='Enabled']"
        );

        wait.until(ExpectedConditions.elementToBeClickable(enabledOption)).click();

// ===== SEARCH =====
        driver.findElement(By.xpath("//button[.=' Search ']")).click();

// ===== VALIDASI =====
        boolean result = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@role='cell' and normalize-space()='Enabled']")
                )
        ).isDisplayed();

        System.out.println(result
                ? "TC_003_03 PASS : Status Enabled tampil"
                : "TC_003_03 FAIL");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}
