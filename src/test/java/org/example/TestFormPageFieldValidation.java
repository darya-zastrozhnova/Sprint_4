package org.example;

import org.example.locators_page_object.OrderForms;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


@RunWith(Parameterized.class)
public class TestFormPageFieldValidation {
    // private FirefoxDriver driver;
    private ChromeDriver driver;
    private final String name;
    private final String familia;
    private final String address;
    private final String telephone;


    public TestFormPageFieldValidation(String name, String familia, String address, String telephone) {
        this.name = name;
        this.familia = familia;
        this.address = address;
        this.telephone = telephone;
    }

    @Before
    public void startUp() {
        // driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/order");

    }

    @Parameterized.Parameters
    public static Object[][] getTextData() {
        return new Object[][]{
                {"Дарья", "Леонидовна", "world ", "loce"},
                {"Furs", "Владимировна", "Время лечит", "8800355355"},
                {"Дарь67я", "МногоМногоМногобуквоченьмногобуквДолжнабытьОшибка", "Нет четких данных по валидации 1123 ", "123123321321"},


        };
}

    @Test
    public void testOrderForms() {
        OrderForms orderForms = new OrderForms(driver);

        orderForms.sendName(name);
        orderForms.sendFamilia(familia);
        orderForms.sendAddress(address);
        orderForms.sendTelephone(telephone);
        orderForms.clickButtonDalee();


        boolean isValidName = name.matches("(.*[a-zA-Z0-9\\s].*)|(.{0,2})|(.{16,})");
        if (isValidName) {

            WebElement errorMessageName = driver.findElement(orderForms.locatorErrorMessageName);
            Assert.assertTrue("Элемент не виден на странице", errorMessageName.isDisplayed());
        }

        boolean isValidFamilia = familia.matches("(.*[a-zA-Z0-9\\s].*)|(.{0,2})|(.{16,})");
        if (isValidFamilia) {

            WebElement errorMessageFamilia = driver.findElement(orderForms.locatorErrorMessageFamilia);
            Assert.assertTrue("Элемент не виден на странице", errorMessageFamilia.isDisplayed());

        }

        boolean isValidAddress = address.matches("(.*[a-zA-Z].*)");
        if (isValidAddress) {


            WebElement errorMessageAddress = driver.findElement(orderForms.locatorErrorMessageAddress);
            Assert.assertTrue("Элемент не виден на странице", errorMessageAddress.isDisplayed());

        }
        boolean isValidTelephone = !telephone.matches("^\\+?\\d{11,14}$");
        if (isValidTelephone) {


            WebElement errorMessageTelephone = driver.findElement(orderForms.locatorErrorMessageTelephone);
            Assert.assertTrue("Элемент не виден на странице", errorMessageTelephone.isDisplayed());
        }
    }

    @After
    public void teardown() {

        driver.quit();
    }
}
