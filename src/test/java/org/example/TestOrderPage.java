package org.example;

import org.example.locators_page_object.homePage;
import org.example.locators_page_object.orderForms;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


@RunWith(Parameterized.class)
public class TestOrderPage {
    // private FirefoxDriver driver;
    private ChromeDriver driver;
    private final String button;
    private final String name;
    private final String familia;
    private final String address;
    private final String metro;
    private final String telephone;
    private final String data;
    private final String rentalPeriod;
    private final String colorScooter;

    public TestOrderPage(String button, String name, String familia, String address, String metro, String telephone, String data, String rentalPeriod, String colorScooter) {
        this.button = button;
        this.name = name;
        this.familia = familia;
        this.address = address;
        this.metro = metro;
        this.telephone = telephone;
        this.data = data;
        this.rentalPeriod = rentalPeriod;
        this.colorScooter = colorScooter;
    }

    @Before
    public void startUp() {
        // driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

    }

    @Parameterized.Parameters
    public static Object[][] getTextData() {
        return new Object[][]{
                {"По центру", "Дарья", "Леонидовна", "Варшавское шоссе 160", "Аннино", "123456789163", "21.10.2025", "трое суток", "Черный"},
                {"Сверху", "Иван", "Иванов", "Мир 12", "Пуш", "987654321654", "29.12.2025", "шестеро суток", "Любой"},


        };
    }

    @Test
    public void testOrderForms() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        homePage.waitHederHomePage(wait);
        homePage homePage = new homePage(driver);
        homePage.clickButtonOrderHederOrDown(button);
        orderForms orderForms = new orderForms(driver);
        org.example.locators_page_object.orderForms.waitHederOrderForms(wait);
        orderForms.sendName(name);
        orderForms.sendFamilia(familia);
        orderForms.sendAddress(address);
        orderForms.sendMetro(metro);
        orderForms.sendTelephone(telephone);
        orderForms.clickButtonDalee();
        orderForms.sendDataAndEnter(data);
        orderForms.clickRentalPeriod(rentalPeriod);
        orderForms.clickColorScooter(colorScooter);
        orderForms.clickButtonZakaz();
        orderForms.clickButtonDa();
        Assert.assertTrue(orderForms.truOrFalseButtonStatusOrder());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
