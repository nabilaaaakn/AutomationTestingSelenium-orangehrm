import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TC_004_02 {

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

        // ===== RECRUITMENT =====
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='Recruitment']"))
        ).click();

        // ===== VACANCIES =====
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[text()='Vacancies']"))
        ).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h5[text()='Vacancies']"))
        );

        // ===== ADD =====
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[.=' Add ']"))
        ).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[text()='Add Vacancy']"))
        );

        // ===== VACANCY NAME =====
        driver.findElement(By.xpath("//label[text()='Vacancy Name']/following::input[1]"))
                .sendKeys("QA Automation " + System.currentTimeMillis());

        // ===== JOB TITLE =====
        By jobTitleDropdown = By.xpath(
                "//label[text()='Job Title']/following::div[contains(@class,'oxd-select-text')][1]"
        );
        wait.until(ExpectedConditions.elementToBeClickable(jobTitleDropdown)).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='option' and normalize-space()='QA Engineer']"))
        ).click();

        // ===== DESCRIPTION =====
        driver.findElement(By.xpath("//textarea"))
                .sendKeys("Automation vacancy description");

        // ===== NUMBER OF POSITIONS =====
        driver.findElement(
                By.xpath("//label[text()='Number of Positions']/following::input[1]")
        ).sendKeys("2");

        // ❌ HIRING MANAGER TIDAK DIISI (INTENTIONALLY SKIPPED)

        // ===== SAVE =====
        driver.findElement(By.xpath("//button[@type='submit' and .=' Save ']")).click();

        // ===== VALIDASI ERROR REQUIRED =====
        boolean errorRequired = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//span[contains(@class,'oxd-input-field-error-message') and text()='Required']")
                )
        ).isDisplayed();

        if (errorRequired) {
            System.out.println("TC_004_02 PASS : Validasi Hiring Manager muncul (Required)");
        } else {
            System.out.println("TC_004_02 FAIL");
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}
