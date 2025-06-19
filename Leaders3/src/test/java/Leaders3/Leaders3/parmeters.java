package Leaders3.Leaders3;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;




public class parmeters {// البيانات الثابتة
	   public static String BASE_URL = "https://leaders.jo/en/buy-apple-products-online/";
	   public static  String HOME_URL = "https://www.leaders.jo/";
	   public static    String SEARCH_TERM = "iPhone";
	   public static  String EXPECTED_CURRENCY = "JOD";
	   String ExpectedDefaultLanage = "en-US";
	   
JavascriptExecutor js = (JavascriptExecutor) driver;
String firstNameRandom = "FN_" + UUID.randomUUID().toString().substring(0, 5);
String lastNameRandom = "LN_" + UUID.randomUUID().toString().substring(0, 5);
 public static WebDriver driver;
 final int EXPECTED_WIDTH = 300;
 final int EXPECTED_HEIGHT = 300;
 Random rand = new Random();

 // تهيئة المتصفح
 @BeforeTest
 public static void initDriver() {
     
     driver = new ChromeDriver();
		driver.manage().window().maximize();
     driver.get(BASE_URL);
 }



 
 public void takeScreenshot(String name) {
	    File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(2025);
	    String fileName = "./screenshots/" + name + "_" + timestamp + ".png";
	    try {
	        FileUtils.copyFile(srcFile, new File(fileName));
	        System.out.println("📸 Screenshot saved: " + fileName);
	    } catch (IOException e) {
	        System.out.println("❌ Failed to save screenshot: " + e.getMessage());
	    }
	}

 
}
