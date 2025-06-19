package Leaders3.Leaders3;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class AppTest extends parmeters {

	@Test(priority = 1, enabled = true)
	public void CheckTheDefaultLangugeIsEnglish() throws InterruptedException {
		String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
		Assert.assertEquals(ActualLanguage, ExpectedDefaultLanage);
		Thread.sleep(1000);
	}

	@Test(priority = 2, enabled = true)
	public void startup() throws InterruptedException {
	    List<WebElement> homeButtons = driver.findElements(By.cssSelector(".motta-button.error-404__button"));
	    if (homeButtons.isEmpty()) {
	        takeScreenshot("No_Home_Button_Found");
	        System.out.println("❌ No home button found on the 404 page.");
	        return;
	    }

	    List<WebElement> appleLinks = driver.findElements(By.cssSelector("a[href*='apple-jordan']"));
	    if (!appleLinks.isEmpty()) {
	        String dataImage = appleLinks.get(0).getAttribute("data-image");

	        if (dataImage != null && dataImage.contains("Apple-banner")) {
	            System.out.println("✅ Apple link and banner image found.");
	        } else {
	            System.out.println("⚠️ Apple link found, but no banner image.");
	            takeScreenshot("Missing_Apple_Banner");
	        }

	        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight / 2);");
	        Thread.sleep(2000);
	        homeButtons.get(0).click();
	        Thread.sleep(1000);
	        System.out.println("⚠️ Navigated to homepage.");
	    } else {
	        System.out.println("❌ Apple link not found.");
	        takeScreenshot("Apple_Link_Not_Found");
	    }
	}


	@Test(priority = 3, enabled = true)
	public void assstestPageLoadsCorrectly() throws InterruptedException {
		driver.findElement(By.cssSelector("a[data-title='Apple']")).click();
		Assert.assertTrue(driver.getTitle().contains("Apple"), "Page title should contain 'Apple'");
		Thread.sleep(1000);

		WebElement appleLink = driver.findElement(By.cssSelector("a[href*='apple-jordan']"));
		Assert.assertEquals(appleLink.getAttribute("data-title"), "Apple");
		Thread.sleep(1000);
		Assert.assertTrue(appleLink.getAttribute("data-image").contains("Apple-banner"));
		Assert.assertTrue(appleLink.isDisplayed());
		Thread.sleep(1000);
		Assert.assertTrue(appleLink.isEnabled());
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,300)");
		js.executeScript("window.scrollTo(0,400)");
		Thread.sleep(4000);
		js.executeScript("window.scrollTo(0,0)");
	}

	@Test(priority = 4, enabled = true)
	public void appleContentisEmpty() throws InterruptedException {
		List<WebElement> appleContent = driver
				.findElements(By.cssSelector("a[href*='apple-jordan'], img[src*='apple'], [data-image*='apple']"));
		if (!appleContent.isEmpty()) {
			System.out.println("✅ Apple content found on the page.");
		} else {
			System.out.println("⚠️ No Apple content found on the page.");
		}
		Thread.sleep(5000);
	}

	@Test(priority = 5, enabled = true)
	public void testAllProductsHaveImages() throws InterruptedException {
		List<WebElement> products = driver.findElements(By.cssSelector(".product-inner"));
		boolean allHaveImages = true;
		Thread.sleep(2000);

		for (WebElement product : products) {
			List<WebElement> images = product.findElements(By.tagName("img"));
			if (images.isEmpty()) {
				allHaveImages = false;
				System.out.println("❌ A product is missing an image.");
				Thread.sleep(2000);
			}
		}

		if (allHaveImages) {
			System.out.println("✅ All products have images.");
		} else {
			System.out.println("❌ Some products are missing images.");
		}
		Thread.sleep(1000);
	}

	@Test(priority = 6, enabled = true)
	public void verifyProductImagesVisibility() throws InterruptedException {
		List<WebElement> products = driver.findElements(By.cssSelector("div.product-inner"));
		int total = products.size();
		int withImages = 0;

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight / 2);");

		for (WebElement product : products) {
			try {
				WebElement image = product.findElement(By.cssSelector("img"));
				boolean displayed = image.isDisplayed();
				int width = image.getSize().getWidth();
				int height = image.getSize().getHeight();

				if (displayed && width >= 100 && height >= 100) {
					withImages++;
				}
				

			} catch (NoSuchElementException e) {
				System.out.println("❌ Product without image element.");
			}
		}

		System.out.println("✅ Products with visible images: " + withImages + "/" + total);
		Thread.sleep(2000);
		js.executeScript("window.scrollTo(0, 0);");
		Thread.sleep(2000);
	}

	@Test(priority = 7, enabled = true)
	public void clickAccountAndSignIn() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 0);");

		WebElement accountButton = driver.findElement(By.cssSelector("a[href*='my-account']"));
		accountButton.click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//a[@href='https://leaders.jo/en/my-account/#register']")).click();

		String randomEmail = "test" + System.currentTimeMillis() + "@example.com";
		String randomPassword = "Test@" + System.currentTimeMillis();

		WebElement emailField = driver.findElement(By.id("reg_email"));
		emailField.clear();
		emailField.sendKeys(randomEmail);
		Thread.sleep(2000);

		WebElement passwordField = driver.findElement(By.id("reg_password"));
		passwordField.clear();
		passwordField.sendKeys(randomPassword);

		Assert.assertEquals(emailField.getAttribute("value"), randomEmail);
		Assert.assertEquals(passwordField.getAttribute("value"), randomPassword);

		js.executeScript("window.scrollTo(0,300)");
		Thread.sleep(2000);

		WebElement registerButton = driver.findElement(By.cssSelector("button[name='register']"));
		registerButton.click();

		driver.findElement(By.cssSelector("a[data-title='Apple']")).click();
	}

	@Test(priority = 8, enabled = true)
	public void sort() throws InterruptedException {
		WebElement sortDropdown = driver.findElement(By.name("orderby"));
		Select select = new Select(sortDropdown);
		select.selectByValue("price");
		Thread.sleep(2000);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight / 2);");

		List<WebElement> products = driver.findElements(By.cssSelector(".products .product"));
		Thread.sleep(2000);

		Assert.assertTrue(products.size() > 1);
		js.executeScript("window.scrollTo(0, 0);");
		System.out.println("Displayed products count: " + products.size());
		Thread.sleep(5000);
	}

	@Test(priority = 9, enabled = true)
	public void addToCartWithQuantityAndProceedToCheckout() throws InterruptedException {
	    List<WebElement> products = driver.findElements(By.className("product-inner"));

	    if (products.isEmpty()) {
	        System.out.println("❌ لا توجد منتجات ظاهرة.");
	        return;
	    }

	    // اختيار منتج عشوائي
	    WebElement randomProduct = products.get(new Random().nextInt(products.size()));
	    new Actions(driver).doubleClick(randomProduct).perform();
	    Thread.sleep(5000);

	    // محاولة اختيار لون من الألوان المتوفرة
	    String[] colorOptions = { 
	        "wcboost-variation-swatches__item-winter-blue-en",
	        "wcboost-variation-swatches__item-storm-blue-en" 
	    };
	    boolean colorClicked = false;

	    Thread.sleep(5000);
	    for (String colorClass : colorOptions) {
	        try {
	            WebElement colorElement = driver.findElement(By.xpath("//li[contains(@class, '" + colorClass + "')]"));
	            if (colorElement.isDisplayed()) {
	                colorElement.click();
	                Thread.sleep(3000);
	                System.out.println("✅ تم اختيار اللون: " + colorClass);
	                colorClicked = true;
	                break;
	            } else {
	                System.out.println("⚠️ العنصر " + colorClass + " غير ظاهر.");
	            }
	        } catch (Exception e) {
	            System.out.println("ℹ️ لم يتم العثور على: " + colorClass);
	        }
	    }

	    // تعديل الكمية باستخدام أزرار + و -
	    List<WebElement> plusButtons = driver.findElements(By.cssSelector(
	        "form[class='cart'] span.motta-svg-icon.motta-svg-icon--plus.motta-qty-button.increase svg"));
	    List<WebElement> minusButtons = driver.findElements(By.cssSelector(
	        "form.cart span.motta-svg-icon.motta-svg-icon--minus.motta-qty-button.decrease svg"));

	    if (!plusButtons.isEmpty() && plusButtons.get(0).isDisplayed() &&
	        !minusButtons.isEmpty() && minusButtons.get(0).isDisplayed()) {

	        WebElement plusButton = plusButtons.get(0);
	        WebElement minusButton = minusButtons.get(0);

	        plusButton.click();
	        Thread.sleep(3000);
	        minusButton.click();
	        Thread.sleep(3000);
	        plusButton.click();
	        Thread.sleep(2000);

	        System.out.println("✅ تم تعديل الكمية باستخدام + و -.");
	    } else {
	        System.out.println("⚠️ لم يتم العثور على أزرار تغيير الكمية.");
	    }

	    // إضافة المنتج إلى السلة
	    WebElement addToCartButton = driver.findElement(By.cssSelector(".button.single_add_to_cart_button"));
	    addToCartButton.click();
	    Thread.sleep(8000);
	    System.out.println("✅ تم النقر على زر 'أضف إلى السلة'.");

	    // النقر على زر "الدفع"
	    WebElement checkoutButton = driver.findElement(By.cssSelector(
	        "div.cart-dropdown.dropdown-content.motta-open a.motta-button.checkout.wc-forward.motta-button--large"));
	    checkoutButton.click();
	    Thread.sleep(5000);
	    System.out.println("✅ تم النقر على زر 'الدفع'.");
	}


	@Test(priority = 10, enabled = true)
	public void enterRandomBillingInfo() throws InterruptedException {
	    Thread.sleep(5000);

	    // إنشاء بيانات عشوائية
	    String firstName = "Test";
	    String lastName = "User";
	    String email = "testuser_" + UUID.randomUUID().toString().substring(0, 6) + "@gemail.com";

	    // إدخال البيانات
	    WebElement firstNameField = driver.findElement(By.id("billing_first_name"));
	    WebElement lastNameField = driver.findElement(By.id("billing_last_name"));
	    WebElement emailField = driver.findElement(By.id("billing_email"));

	    firstNameField.clear();
	    lastNameField.clear();
	    emailField.clear();

	    firstNameField.sendKeys(firstName);
	    Thread.sleep(2000); 
	    lastNameField.sendKeys(lastName);
	    Thread.sleep(3000);  emailField.sendKeys(email);

	    Thread.sleep(5000); // بعد الإدخال

	    // تحقق أن البيانات المدخلة صحيحة
	    Assert.assertEquals(firstNameField.getAttribute("value"), firstName);
	    Assert.assertEquals(lastNameField.getAttribute("value"), lastName);
	    Assert.assertEquals(emailField.getAttribute("value"), email); 
	    WebElement secondOffersLink = driver.findElement(By.xpath("(//a[normalize-space()='Offers'])[2]"));
        secondOffersLink.click();

	}
	@Test(priority = 11, enabled = true)
	public void checkAllProductPrices() throws InterruptedException, IOException {
	    Thread.sleep(5000); // Wait for products to load

	    List<WebElement> allProducts = driver.findElements(By.xpath("//li[contains(@class,'product')]"));
	    System.out.println("Total products found: " + allProducts.size());

	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    boolean hasBug = false; // 🔁 متغير لتتبع الأخطاء

	    for (int i = 0; i < allProducts.size(); i++) {
	        WebElement product = allProducts.get(i);

	        try {
	            // Scroll to product
	            js.executeScript("arguments[0].scrollIntoView(true);", product);
	            Thread.sleep(1000);

	            // Get current price
	            WebElement currentPriceElement = product.findElement(By.xpath(".//span[@class='price']//ins/span[contains(@class,'woocommerce-Price-amount')]"));
	            double currentPrice = Double.parseDouble(currentPriceElement.getText().replace("JOD", "").replace(",", "").trim());
	            System.out.println("✅ Product " + (i + 1) + " - Current price: " + currentPrice);

	            // Check for original price
	            List<WebElement> originalPriceElements = product.findElements(By.xpath(".//span[@class='price']//del/span[contains(@class,'woocommerce-Price-amount')]"));
	            if (!originalPriceElements.isEmpty()) {
	                double originalPrice = Double.parseDouble(originalPriceElements.get(0).getText().replace("JOD", "").replace(",", "").trim());
	                System.out.println("🧾 Original price: " + originalPrice);

	                if (currentPrice >= originalPrice) {
	                    System.out.println("❌ BUG: Discounted price is NOT less than original");

	                    // Screenshot
	                    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	                    String screenshotPath = "./screenshot_product_" + (i + 1) + ".png";
	                    FileUtils.copyFile(screenshot, new File(screenshotPath));
	                    System.out.println("📸 Screenshot saved: " + screenshotPath);

	                    // 🐞 Bug Report
	                    System.out.println("🐞 BUG REPORT:");
	                    System.out.println("Product Index: " + (i + 1));
	                    System.out.println("Current Price: " + currentPrice);
	                    System.out.println("Original Price: " + originalPrice);
	                    System.out.println("Issue: Discounted price is NOT lower than original.");
	                    System.out.println("Screenshot: " + screenshotPath);
	                    hasBug = true; // 🚩 و
                       Assert.assertFalse(hasBug, "❌ One or more products have incorrect price discounts.");	                   
	                }
	            } else {
	                System.out.println("ℹ️ No original price (no discount) for product " + (i + 1));
	            }

	        } catch (Exception e) {
	            System.out.println("⚠️ Skipped product " + (i + 1) + " due to error: " + e.getMessage());
	        }
	    }

	    // ✅ Assert at the end
	   
	}

}
