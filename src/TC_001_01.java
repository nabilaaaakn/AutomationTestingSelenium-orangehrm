import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TC_001_01 {

    WebDriver driver;

    public void testLoginSuccess() {
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        boolean dashboard =
                driver.findElement(By.xpath("//h6[text()='Dashboard']")).isDisplayed();

        if (dashboard) {
            System.out.println("TC-001-01 PASS");
        } else {
            System.out.println("TC-001-01 FAIL");
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.quit();
    }

    public static void main(String[] args) {
        TC_001_01 test = new TC_001_01();
        test.testLoginSuccess();
    }
}
