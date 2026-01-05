import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {

    WebDriver driver;

    public void launchBrowser() {
        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    public static void main(String[] args) {
        Main obj = new Main();
        obj.launchBrowser();
    }
}