package org.example.locators_page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class homePage {
    private final WebDriver driver;

    public homePage(WebDriver driver) {
        this.driver = driver;

    }

    // Локатор "Главной страницы"
    private static final By locatorHederHomePage = (By.className("Header_Disclaimer__3VEni"));

    // Логотип Яндекс
    private final By locatorLogoYandex = By.className("Header_LogoYandex__3TSOI");

    // Логотип Самокат
    private final By locatorLogoSamocat = By.cssSelector("html img[alt='Scooter']");

    // Кнопка Заказать в хедере сверху справа
    private final By locatorButtonOrderHeder = By.xpath(".//div[@class = 'Header_Nav__AGCXC']/button[@class = 'Button_Button__ra12g']");

    // Кнопка Заказать по центру
    private final By locatorButtonOrderDown = By.xpath(".//div[@class = 'Home_FinishButton__1_cWm']/button");

    //Статус заказа
    private final By locatorButtonStatusOrderHeder = By.className("Header_Link__1TAG7");

    // Поле ввода номера заказа
    private final By locatorInputStatusOrderHeder = By.xpath(".//div[@class = 'Header_SearchInput__3YRIQ']/div/input");

    // Кнопка GO для проверки статуса заказ
    private final By locatorButtonGoStatusOrderHeder = By.xpath(".//div[@class = 'Header_SearchInput__3YRIQ']/button");

    // Локаторк картинки Нет заказа
    public static final By locatorNotFound = By.xpath(".//div[@class= 'Track_NotFound__6oaoY']/img");


    // Загрузка главной страницы
    public static void waitHederHomePage(WebDriverWait wait) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locatorHederHomePage));
    }


    // Клик по лого Яндекс
    public void clickOnLogoYandex() {
        driver.findElement(locatorLogoYandex).click();
    }


    // Клик по лого Самокат
    public void clickOnLogoSamocat() {
        driver.findElement(locatorLogoSamocat).click();
    }


    // Клик по верхней кнопке Заказать
    public void clickOnButtonOrderHeder() {
        driver.findElement(locatorButtonOrderHeder).click();
    }


    // Клик по кнопке заказать по центру
    public void clickButtonOrderDown() {

        WebElement questionElement = driver.findElement(locatorButtonOrderDown);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", questionElement);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locatorButtonOrderDown));

        questionElement.click();
    }

    // выбор через Какую кнопку сделать заказ, по умолчанию сверху.
    public void clickButtonOrderHederOrDown(String button) {

        if (button.equals("По центру")) {
            clickButtonOrderDown();
        } else {
            clickOnButtonOrderHeder();
        }

    }

    // Клик по статусу заказа
    public void clickButtonStatusOrder() {
        driver.findElement(locatorButtonStatusOrderHeder).click();
    }


    // Ввод номера заказ
    public void sendNumberOrderStatus(String orderNumber) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        WebElement element = driver.findElement(locatorInputStatusOrderHeder);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locatorInputStatusOrderHeder));
        element.sendKeys(orderNumber);
    }


    // Клик по GO
    public void clickButtonGoOrderStatus() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        WebElement element = driver.findElement(locatorButtonGoStatusOrderHeder);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locatorButtonGoStatusOrderHeder));
        element.click();
    }

    // Скрол и клик по номеру вопроса
    public void scrollAndClickQuestionNumber(WebDriverWait wait, int number) {
        String questionId = "accordion__heading-" + (number - 1);
        By locatorsQuestions = By.id(questionId);

        WebElement questionElement = driver.findElement(locatorsQuestions);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", questionElement);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locatorsQuestions));
        questionElement.click();
    }

    // получение текста из ответа на вопрос по номеру.
    public String getTextAnswerNumber(WebDriverWait wait, int number) {
        String answerId = "accordion__panel-" + (number - 1);
        By locatorsAnswers = By.id(answerId);

        wait.until(ExpectedConditions.visibilityOfElementLocated(locatorsAnswers));
        return driver.findElement(locatorsAnswers).getText();
    }
}