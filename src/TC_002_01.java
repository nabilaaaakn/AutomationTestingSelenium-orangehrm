import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


import java.time.Duration;
import java.util.List;

public class TC_002_01 {

    WebDriver driver;
    WebDriverWait wait;

    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    public void runTest() {
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

        // pastikan Add User page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Add User']")));

        // ===== USER ROLE (keyboard) =====
        driver.findElement(By.xpath("(//div[contains(@class,'oxd-select-text-input')])[1]"))
                .sendKeys(Keys.ARROW_DOWN, Keys.ENTER);

        // ===== EMPLOYEE NAME (REAL CLICK - FINAL) =====
        By empInput = By.xpath("//input[@placeholder='Type for hints...']");

        WebElement input = driver.findElement(empInput);
        // trigger autocomplete
        input.clear();
        input.sendKeys("Daniel");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        input.sendKeys(Keys.SPACE);


// tunggu dropdown
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'oxd-autocomplete-dropdown')]")
        ));

// cari option Daniel Kang LANGSUNG
        By danielKang = By.xpath(
                "//div[contains(@class,'oxd-autocomplete-option')][normalize-space()='Daniel Kang']"
        );

        WebElement option = wait.until(
                ExpectedConditions.elementToBeClickable(danielKang)
        );

// klik (Selenium 4 compatible)
        new Actions(driver)
                .moveToElement(option)
                .pause(Duration.ofMillis(200))
                .click()
                .perform();

// blur
        input.sendKeys(Keys.TAB);


        // ===== STATUS (keyboard) =====
        driver.findElement(By.xpath("(//div[contains(@class,'oxd-select-text-input')])[2]"))
                .sendKeys(Keys.ARROW_DOWN, Keys.ENTER);

        // ===== USERNAME =====
        driver.findElement(By.xpath("//label[text()='Username']/following::input[1]"))
                .sendKeys("user" + System.currentTimeMillis());

        // ===== PASSWORD =====
        driver.findElement(By.xpath("(//input[@type='password'])[1]"))
                .sendKeys("Password123");
        driver.findElement(By.xpath("(//input[@type='password'])[2]"))
                .sendKeys("Password123");

        // ===== SAVE =====
        By saveBtn = By.xpath("//button[@type='submit' and .=' Save ']");

        wait.until(ExpectedConditions.elementToBeClickable(saveBtn));

        // click dua kali aman (UI kadang miss)
        driver.findElement(saveBtn).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(saveBtn).click();


        // ===== VALIDASI (JIKA ADA) =====
        boolean success;

        try {
            success = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//p[contains(text(),'Successfully Saved')]"))
            ).isDisplayed();
        } catch (Exception e) {
            // fallback: cek balik ke halaman Users
            success = driver.getCurrentUrl().contains("/admin/viewSystemUsers");
        }

        if (success) {
            System.out.println("TC_002_01 PASS : User berhasil ditambahkan");
        } else {
            System.out.println("TC_002_01 FAIL");
        }


        driver.quit();
    }

    public static void main(String[] args) {
        new TC_002_01().runTest();
    }
}
