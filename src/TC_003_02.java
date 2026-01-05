import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TC_003_02 {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        // ===== LOGIN =====
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")))
                .sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // ===== ADMIN MENU =====
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='Admin']"))
        ).click();

        // ===== PASTIKAN USERS PAGE SUDAH LOAD =====
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h5[text()='System Users']"))
        );

        // ===== FILTER USER ROLE = ADMIN =====
        By userRoleDropdown = By.xpath(
                "//label[text()='User Role']/following::div[contains(@class,'oxd-select-text')][1]"
        );

        wait.until(ExpectedConditions.elementToBeClickable(userRoleDropdown)).click();

        By adminOption = By.xpath(
                "//div[@role='option' and normalize-space()='ESS']"
        );

        wait.until(ExpectedConditions.elementToBeClickable(adminOption)).click();

// ===== SEARCH =====
        driver.findElement(By.xpath("//button[.=' Search ']")).click();

// ===== VALIDASI =====
        boolean result = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@role='cell' and normalize-space()='ESS']")
                )
        ).isDisplayed();


        if (result) {
            System.out.println("TC_003_02 PASS : User Role ESS tampil");
        } else {
            System.out.println("TC_003_02 FAIL");
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}
