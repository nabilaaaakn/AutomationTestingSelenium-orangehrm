import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TC_002_02 {

    WebDriver driver;
    WebDriverWait wait;

    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    public void testAddUserFailed() {
        setup();

        // ===== LOGIN =====
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")))
                .sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // ===== ADMIN =====
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Admin']"))).click();

        // ===== ADD USER =====
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.=' Add ']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Add User']")));

        // ===== USER ROLE =====
        driver.findElement(By.xpath("(//div[contains(@class,'oxd-select-text-input')])[1]"))
                .sendKeys(Keys.ARROW_DOWN, Keys.ENTER);

        // ===== EMPLOYEE NAME =====
        WebElement input = driver.findElement(By.xpath("//input[@placeholder='Type for hints...']"));
        input.sendKeys("Daniel");
        input.sendKeys(Keys.SPACE);
        input.sendKeys(Keys.BACK_SPACE);

        By danielKang = By.xpath(
                "//div[contains(@class,'oxd-autocomplete-option')][normalize-space()='Daniel Kang']"
        );

        WebElement option = wait.until(
                ExpectedConditions.elementToBeClickable(danielKang)
        );

        new Actions(driver)
                .moveToElement(option)
                .pause(Duration.ofMillis(200))
                .click()
                .perform();

        input.sendKeys(Keys.TAB);

        // ===== STATUS =====
        driver.findElement(By.xpath("(//div[contains(@class,'oxd-select-text-input')])[2]"))
                .sendKeys(Keys.ARROW_DOWN, Keys.ENTER);

        // ===== USERNAME =====
        driver.findElement(By.xpath("//label[text()='Username']/following::input[1]"))
                .sendKeys("user_fail_" + System.currentTimeMillis());

        // ===== PASSWORD (INVALID < 7) =====
        WebElement pwd = driver.findElement(By.xpath("(//input[@type='password'])[1]"));
        WebElement confirmPwd = driver.findElement(By.xpath("(//input[@type='password'])[2]"));

        pwd.sendKeys("123");
        confirmPwd.sendKeys("123");

// 🔥 TRIGGER VALIDASI
        confirmPwd.sendKeys(Keys.TAB);


        // ===== SAVE =====
        driver.findElement(By.xpath("//button[@type='submit' and .=' Save ']")).click();

        // ===== VALIDASI ERROR PASSWORD =====
        boolean errorPassword = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//span[contains(@class,'oxd-input-field-error-message')]")
                )
        ).isDisplayed();


        if (errorPassword) {
            System.out.println("TC_002_02 PASS : Validasi password muncul");
        } else {
            System.out.println("TC_002_02 FAIL");
        }

        try {
            Thread.sleep(10000); // 10.000 ms = 10 detik
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }

    public static void main(String[] args) {
        new TC_002_02().testAddUserFailed();
    }
}
