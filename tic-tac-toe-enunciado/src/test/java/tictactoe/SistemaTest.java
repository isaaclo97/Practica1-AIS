package tictactoe;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import es.codeurjc.ais.tictactoe.*;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SistemaTest {
   protected WebDriver driver;
   protected WebDriver driver1;
    @BeforeClass
    public static void setupClass() {
    ChromeDriverManager.getInstance().setup();
    }
    @Before
    public void setupTest() {
    driver = new ChromeDriver();
    driver1 = new ChromeDriver();
    }
    @After
    public void teardown() {
    if (driver != null) {
    driver.quit();
    }
    if (driver1 != null) {
    driver1.quit();
    }
    }
    @Test
    public void nick () throws InterruptedException {
        driver.get("http://localhost:8080/");
        driver1.get("http://localhost:8080/");
        String addnick = "Isaac";
        String addnick1 = "Jorge";
        driver.findElement(By.id("nickname")).sendKeys(addnick);
        driver1.findElement(By.id("nickname")).sendKeys(addnick1);
        addnick = "X "+addnick;
        addnick1 = addnick1 +" O";
        driver.findElement(By.id("startBtn")).click();
        driver1.findElement(By.id("startBtn")).click();
        String nick1 = driver.findElement(By.id("p1Score")).getText();
        String nick11 = driver1.findElement(By.id("p1Score")).getText();
        String nick2 = driver.findElement(By.id("p2Score")).getText();
        String nick22 = driver1.findElement(By.id("p2Score")).getText();
        assertThat(addnick).isEqualTo(nick1);
        assertThat(addnick).isEqualTo(nick11);
        assertThat(addnick1).isEqualTo(nick2);
        assertThat(addnick1).isEqualTo(nick22);
    }
     @Test
    public void win () throws InterruptedException {
        driver.get("http://localhost:8080/");
        driver1.get("http://localhost:8080/");
        String addnick = "Isaac";
        String addnick1 = "Jorge";
        driver.findElement(By.id("nickname")).sendKeys(addnick);
        driver1.findElement(By.id("nickname")).sendKeys(addnick1);
        driver.findElement(By.id("startBtn")).click();
        driver1.findElement(By.id("startBtn")).click();
        driver.findElement(By.id("cell-0")).click();
        driver1.findElement(By.id("cell-3")).click();
        driver.findElement(By.id("cell-1")).click();
        driver1.findElement(By.id("cell-4")).click();
        driver.findElement(By.id("cell-2")).click();
        String res = driver.switchTo().alert().getText();
        assertThat(res).isEqualTo(addnick+" wins! "+addnick1+" looses.");
    }
         @Test
    public void draw () throws InterruptedException {
        driver.get("http://localhost:8080/");
        driver1.get("http://localhost:8080/");
        String addnick = "Isaac";
        String addnick1 = "Jorge";
        driver.findElement(By.id("nickname")).sendKeys(addnick);
        driver1.findElement(By.id("nickname")).sendKeys(addnick1);
        driver.findElement(By.id("startBtn")).click();
        driver1.findElement(By.id("startBtn")).click();
        driver.findElement(By.id("cell-0")).click();
        driver1.findElement(By.id("cell-3")).click();
        driver.findElement(By.id("cell-1")).click();
        driver1.findElement(By.id("cell-2")).click();
        driver.findElement(By.id("cell-4")).click();
        driver1.findElement(By.id("cell-7")).click();
        driver.findElement(By.id("cell-5")).click();
        driver1.findElement(By.id("cell-8")).click();
        driver.findElement(By.id("cell-6")).click();
        String res = driver.switchTo().alert().getText();
        assertThat(res).isEqualTo("Draw!");
    }
         @Test
    public void loss () throws InterruptedException {
       driver.get("http://localhost:8080/");
        driver1.get("http://localhost:8080/");
        String addnick = "Isaac";
        String addnick1 = "Jorge";
        driver.findElement(By.id("nickname")).sendKeys(addnick);
        driver1.findElement(By.id("nickname")).sendKeys(addnick1);
        driver.findElement(By.id("startBtn")).click();
        driver1.findElement(By.id("startBtn")).click();
        driver.findElement(By.id("cell-0")).click();
        driver1.findElement(By.id("cell-3")).click();
        driver.findElement(By.id("cell-1")).click();
        driver1.findElement(By.id("cell-4")).click();
        driver.findElement(By.id("cell-6")).click();
        driver1.findElement(By.id("cell-5")).click();
        String res = driver.switchTo().alert().getText();
        assertThat(res).isEqualTo(addnick1+" wins! "+addnick+" looses.");
    }
}