package ohtu;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        Random r = new Random();

        driver.get("http://localhost:4567");

        sleep(2);

//        Scenarios 1-2        
//        WebElement element = driver.findElement(By.linkText("login"));


//        Scenarios 3-4
        WebElement element = driver.findElement(By.linkText("register new user"));
        
//        All scenarios
        element.click();

        sleep(2);

//        Scenario 1
//        element = driver.findElement(By.name("username"));
//        element.sendKeys("pekka");
//        element = driver.findElement(By.name("password"));
//        element.sendKeys("akkep");
//        element = driver.findElement(By.name("login"));


//        Scenario 2
//        element = driver.findElement(By.name("username"));
//        element.sendKeys("pekka");
//        element = driver.findElement(By.name("password"));
//        element.sendKeys("akke");
//        element = driver.findElement(By.name("login"));


//        Scenarios 3-4
        element = driver.findElement(By.name("username"));
        element.sendKeys("salla" + r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("salla123");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("salla123");
        element = driver.findElement(By.name("signup"));

//        All scenarios
        sleep(2);
        
        element.submit();

        sleep(3);

//        Scenarios 3-4
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        
        sleep(2);
        
        element = driver.findElement(By.linkText("logout"));
        element.click();        

//        All scenarios
        driver.quit();
    }

    private static void sleep(int n) {
        try {
            Thread.sleep(n * 1000);
        } catch (Exception e) {
        }
    }
}
