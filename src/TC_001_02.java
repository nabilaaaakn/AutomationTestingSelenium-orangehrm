import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class TC_001_02 {

    WebDriver driver;

    public void launchBrowser() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    public void testLoginFailed() {
        launchBrowser();

        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("passwordSalah");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        boolean errorMuncul =
                driver.findElement(By.xpath("//p[text()='Invalid credentials']")).isDisplayed();

        boolean tetapDiLogin =
                driver.getCurrentUrl().contains("auth/login");

        if (errorMuncul && tetapDiLogin) {
            System.out.println("TC_001_02 PASS : Error muncul & tetap di login page");
        } else {
            System.out.println("TC_001_02 FAIL");
        }

        try {
            Thread.sleep(10000); // 10.000 ms = 10 detik
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.quit();
    }

    public static void main(String[] args) {
        TC_001_02 test = new TC_001_02();
        test.testLoginFailed();
    }
}
