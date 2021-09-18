package geekbrains.homeWork3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CreateCrmMyProject {

    public static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
        loginToCrm();

        Actions actions = new Actions(driver);
        WebElement projectMenuElement = driver.findElement(By.xpath("//a/span[text()='Проекты']"));
//        //span[text()='Проекты']/ancestor::a
        actions.moveToElement(projectMenuElement).perform();
        driver.findElement(By.xpath("//li[@data-route='crm_project_index']/a")).click();


        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Создать проект']")));
        driver.findElement(By.xpath("//a[text()='Создать проект']")).click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@name='crm_project[name]']")));
//      переменную charSequences задать бы рандомом, а то тест негативный
        driver.findElement(By.name("crm_project[name]")).sendKeys("name5");

        driver.findElement(By.xpath("//span[text()='Укажите организацию']")).click();

        driver.findElement(By.xpath("//div[@id='select2-drop']//input")).sendKeys("test");

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='select2-result-label']")));
        List<WebElement> organizationVars = driver.findElements(By.xpath("//div[@class='select2-result-label']"));
        organizationVars.get(3).click();

        Select businessUnitSelect = new Select(driver.findElement(By.name("crm_project[businessUnit]")));
        businessUnitSelect.selectByVisibleText("Research & Development");

        Select curatorUnitSelect = new Select(driver.findElement(By.name("crm_project[curator]")));
        curatorUnitSelect.selectByVisibleText("Юзеров Юзер");

        Select projectUnitSelect = new Select(driver.findElement(By.name("crm_project[rp]")));
        projectUnitSelect.selectByVisibleText("Дедова Маргарита");

        Select managerUnitSelect = new Select(driver.findElement(By.name("crm_project[manager]")));
        managerUnitSelect.selectByVisibleText("Новиков Андрей");

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='select2-container select2']")));
        driver.findElement(By.xpath("//div[contains(@id, 's2id_crm_project_contactMain-uid')]/a")).click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='select2-drop']//input")));
        driver.findElement(By.xpath("//div[@id='select2-drop']//input")).sendKeys("Петров Иван Иванович");
        driver.findElement(By.xpath("//div[@id='select2-drop']//input")).sendKeys(Keys.ENTER);

       driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id, 'crm_project_planning-uid')]")));
       driver.findElement(By.xpath("//body")).sendKeys("Hello!");
       driver.switchTo().parentFrame();

        driver.findElement(By.xpath("//input[@name='crm_project[skipSpeedChecks]']")).click();

        driver.findElement(By.xpath("//button[contains(text(), 'Сохранить и закрыть')]")).click();

        Thread.sleep(5000);
        driver.quit();
    }

    public static void loginToCrm() {
        driver.get("https://crm.geekbrains.space/user/login");
        driver.findElement(By.id("prependedInput")).sendKeys("Applanatest1");
        driver.findElement(By.id("prependedInput2")).sendKeys("Student2020!");
        driver.findElement(By.xpath("//button")).click();
    }
}
