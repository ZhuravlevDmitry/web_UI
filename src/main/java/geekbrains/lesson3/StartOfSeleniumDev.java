package geekbrains.lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class StartOfSeleniumDev {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://ya.ru");
        Thread.sleep(5000);
        driver.quit();

        WebDriverManager.firefoxdriver().setup();
        WebDriver firefoxdriver = new FirefoxDriver();
       firefoxdriver.get("https://ya.ru");
        Thread.sleep(5000);
        firefoxdriver.quit();
    }
}
