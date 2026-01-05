import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TC_004_01 {

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

        // ===== HIRING MANAGER (AUTOCOMPLETE - SAME AS ADD USER) =====
        By hmInput = By.xpath("//input[@placeholder='Type for hints...']");
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(hmInput));

        // ===== DESCRIPTION =====
        driver.findElement(By.xpath("//textarea"))
                .sendKeys("Automation vacancy description");

// ===== NUMBER OF POSITIONS =====
        driver.findElement(
                By.xpath("//label[text()='Number of Positions']/following::input[1]")
        ).sendKeys("2");

        // trigger autocomplete
        input.clear();
        input.sendKeys("Budi");
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

        // pilih manager spesifik
        By budiPekerti = By.xpath(
                "//div[contains(@class,'oxd-autocomplete-option')][normalize-space()='Budi Pekerti']"
        );

        WebElement option = wait.until(
                ExpectedConditions.elementToBeClickable(budiPekerti)
        );

        new Actions(driver)
                .moveToElement(option)
                .pause(Duration.ofMillis(200))
                .click()
                .perform();

        // blur supaya validasi hilang
        input.sendKeys(Keys.TAB);

        // ===== SAVE =====
        driver.findElement(By.xpath("//button[@type='submit' and .=' Save ']")).click();

        // ===== VALIDASI =====
        boolean success;
        try {
            success = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'Successfully Saved')]"))
            ).isDisplayed();
        } catch (Exception e) {
            success = driver.getCurrentUrl().contains("/recruitment/viewJobVacancy");
        }

        if (success) {
            System.out.println("TC_004_01 PASS : Vacancy berhasil ditambahkan");
        } else {
            System.out.println("TC_004_01 FAIL");
        }

        driver.quit();
    }
}
