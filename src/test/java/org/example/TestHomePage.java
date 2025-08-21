package org.example;

import org.example.locators_page_object.homePage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class TestHomePage {
    // static FirefoxDriver driver;
    static ChromeDriver driver;

    @Before
    public void startUp() {
        // driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        homePage.waitHederHomePage(wait);
    }

    @Test
    public void testLogoYandex() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        homePage homePage = new homePage(driver);
        String currentWindowHandle = driver.getWindowHandle();
        homePage.clickOnLogoYandex();
        wait.until(webDriver -> webDriver.getWindowHandles().size() > 1);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(currentWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        assertEquals("Открылось что-то не то", "https://yandex.ru", driver.getCurrentUrl());

    }

    @Test
    public void testLogoSamocat() {

        homePage homePage = new homePage(driver);
        homePage.clickOnButtonOrderHeder();
        homePage.clickOnLogoSamocat();
        String expectedUrl = "https://qa-scooter.praktikum-services.ru/";
        String actualUrl = driver.getCurrentUrl();
        assertEquals("Ожидается, что после клика вернёмся на главную страницу", expectedUrl, actualUrl);
    }

    @Test
    public void testFailedOrderStatus() {
        homePage homePage = new homePage(driver);
        homePage.clickButtonStatusOrder();
        homePage.sendNumberOrderStatus("1085sdf74");
        homePage.clickButtonGoOrderStatus();

        // Задержка для загрузки страницы, картинка с нот фоунд открывается и при верном номере заказа.
        // И по этому тест проходит проверку для верного заказа.

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        Assert.assertTrue(driver.findElement(org.example.locators_page_object.homePage.locatorNotFound).isDisplayed());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}