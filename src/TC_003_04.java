import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TC_003_04 {

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

        // ===== FILTER STATUS = DISABLED =====
        By statusDropdown = By.xpath(
                "//label[text()='Status']/following::div[contains(@class,'oxd-select-text')][1]"
        );

        wait.until(ExpectedConditions.elementToBeClickable(statusDropdown)).click();

        By disabledOption = By.xpath(
                "//div[@role='option' and normalize-space()='Disabled']"
        );

        wait.until(ExpectedConditions.elementToBeClickable(disabledOption)).click();

        // ===== SEARCH =====
        driver.findElement(By.xpath("//button[.=' Search ']")).click();

        // ===== TUNGGU SALAH SATU KONDISI (INI KUNCI) =====
        wait.until(d ->
                !d.findElements(
                        By.xpath("//*[contains(normalize-space(),'No Records Found')]")
                ).isEmpty()
                ||
                !d.findElements(
                        By.xpath("//div[@role='cell']")
                ).isEmpty()
        );

        // ===== VALIDASI HASIL (ADAPTIF & AMAN) =====

        // cek No Records Found (TANPA WAIT)
        List<WebElement> noRecords = driver.findElements(
                By.xpath("//*[contains(normalize-space(),'No Records Found')]")
        );

        if (!noRecords.isEmpty()) {

            // CASE 1: DATA KOSONG
            System.out.println("TC_003_04 PASS : Tidak ada user dengan status Disabled");

        } else {

            // CASE 2: ADA DATA
            List<WebElement> statusCells = driver.findElements(
                    By.xpath("//div[@role='cell' and (normalize-space()='Enabled' or normalize-space()='Disabled')]")
            );

            if (statusCells.isEmpty()) {
                System.out.println("TC_003_04 FAIL : Table kosong tapi tidak ada pesan No Records Found");
            } else {
                boolean allDisabled = statusCells.stream()
                        .allMatch(cell -> cell.getText().trim().equals("Disabled"));

                if (allDisabled) {
                    System.out.println("TC_003_04 PASS : Semua user berstatus Disabled");
                } else {
                    System.out.println("TC_003_04 FAIL : Ada user yang bukan Disabled");
                }
            }
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}
