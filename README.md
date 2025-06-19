
# 🚀 Leaders3 UI Test Automation

This is a **Selenium-based automated UI test suite** for the [Leaders3 website](https://leaders.jo), built with **Java, TestNG, and WebDriver**. It simulates real-world user behavior such as navigating pages, checking product availability, validating discounts, registering accounts, and performing checkout flows.

---

## 📂 Project Structure

```
Leaders3/
│
├── src/test/java/Leaders3/Leaders3/AppTest.java      # Main test class
├── src/test/java/Leaders3/Leaders3/parmeters.java    # Configuration & WebDriver setup
├── pom.xml                                           # Maven dependencies
├── screenshots/                                      # Captured bug screenshots
└── README.md                                         # This file
```

---

## ✅ What This Test Suite Covers

| Test Case No. | Description                                                                         |
| ------------- | ----------------------------------------------------------------------------------- |
| TC1           | Verify default language is English                                                  |
| TC2           | Handle 404 errors, home redirection, and Apple banner checks                        |
| TC3           | Validate page loads correctly for Apple products                                    |
| TC4           | Check for Apple-related content on the homepage                                     |
| TC5           | Verify all product listings include visible images                                  |
| TC6           | Validate visibility and dimensions of all product images                            |
| TC7           | Test user registration with dynamic email and password                              |
| TC8           | Verify sort functionality (e.g., by price)                                          |
| TC9           | Add product to cart with random color & quantity                                    |
| TC10          | Proceed to checkout and fill billing details with random info                       |
| TC11          | Check that discounted prices are less than original prices; report any pricing bugs |

---

## 🧪 Prerequisites

* Java 8+
* Maven
* ChromeDriver (compatible with your installed browser)
* TestNG plugin (if using Eclipse/IDEA)
* Apache Commons IO

Install dependencies with:

```bash
mvn clean install
```

---

## ▶️ Running the Tests

To execute all test cases:

```bash
mvn test
```

Or to run a specific test:

```bash
mvn test -Dtest=AppTest#checkAllProductPrices
```

---

## 📸 Bug Reporting

* Screenshots are automatically taken when:

  * Products are missing images
  * Discounted prices are incorrect
* Screenshots are saved with filenames like:
  `screenshot_product_#.png`

---

## ⚙️ Configuration Notes

Update `parmeters.java` to set:

* WebDriver path
* Base URL
* Timeouts

You can also inject parameters via XML for environments or browsers.

---

## 🛠 Technologies Used

* **Java**
* **Selenium WebDriver**
* **TestNG**
* **Maven**
* **Apache Commons IO**
* **ChromeDriver**

---
