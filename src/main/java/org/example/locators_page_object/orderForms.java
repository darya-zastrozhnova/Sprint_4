package org.example.locators_page_object;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class orderForms {
    private final WebDriver driver;

    public orderForms(WebDriver driver) {
        this.driver = driver;
    }

    //Локатор хедера формы заказа
    private static final By locatorHederOrderForm = By.className("Order_Header__BZXOb");
    //Локатор поля Имя
    public final By locatorInputName = By.xpath(".//input[@class = 'Input_Input__1iN_Z Input_Responsible__1jDKN' and  @placeholder = '* Имя']");
    // Локатор поля Фамилия
    public final By locatorInputFamilia = By.xpath(".//input[@class = 'Input_Input__1iN_Z Input_Responsible__1jDKN' and  @placeholder = '* Фамилия']");
    //Локатор поля Адрес
    private final By locatorInputAddress = By.xpath(".//input[@class = 'Input_Input__1iN_Z Input_Responsible__1jDKN' and  @placeholder = '* Адрес: куда привезти заказ']");
    //Локатор поля Метро
    private final By locatorInputMetro = By.xpath(".//input[@class = 'select-search__input']");
    // Локатор списка станций метро
    private final By locatorStationMetro = By.xpath(".//div[@class = 'select-search__select']");
    // Локатор станции метро 1-й в списке при использовании интерактивного ввода названия
    private final By locatorStationMetroInList = By.xpath(".//li");
    // Локатор поля Телефон
    private final By locatorInputTelephone = By.xpath(".//input[@placeholder = '* Телефон: на него позвонит курьер']");
    // Локатор кнопки Далее в форме заказа
    private final By locatorButtonDalee = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']");
    // Локатор поля Когда привезти самокат
    private final By locatorInputWhen = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    // Локатор Срок аренды
    private final By locatorRentalPeriod = By.xpath(".//div[@class = 'Dropdown-placeholder']");
    // Локатор меню выбора срока аренды
    private final By locatorMenuRentalPeriod = By.xpath(".//div[@class = 'Dropdown-menu']");
    // Локатор черного цвета самоката
    private final By locatorBlackScooter = By.xpath(".//label[@for = 'black']");
    // Локатор серого цвета самоката
    private final By locatorGreyScooter = By.xpath(".//label[@for = 'grey']");
    // Локатор кнопки Да
    private final By locatorButtonDa = By.xpath(".//button[@class= 'Button_Button__ra12g Button_Middle__1CSJM' and contains(text(), 'Да')]");
    // Локатор кнопки Заказать в форме заказа
    private final By locatorButtonZakaz = By.xpath(".//button[@class= 'Button_Button__ra12g Button_Middle__1CSJM' and contains(text(), 'Заказать')]");
    //Локатор кнопки "Посмотреть статус заказ"
    private final By locatorButtonStatusOrder = By.xpath(".//div[@class = 'Order_NextButton__1_rCA']/button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']");
    // Локатор Ошибки поля Имя
    public final By locatorErrorMessageName = By.xpath(".//div[text()='Введите корректное имя']");
    // Локатор Ошибки поля Фамилия
    public final By locatorErrorMessageFamilia = By.xpath(".//div[text()='Введите корректную фамилию']");
    // Локатор Ошибки поля Адрес
    public final By locatorErrorMessageAddress = By.xpath(".//div[text()='Введите корректный адрес']");
    // Локатор Ошибки поля Телефон
    public final By locatorErrorMessageTelephone = By.xpath(".//div[text()='Введите корректный номер']");


    // Ввод в поле Имя
    public void sendName(String Name) {
        driver.findElement(locatorInputName).sendKeys(Name);
    }

    // Ввод в поле Фамилия
    public void sendFamilia(String Familia) {
        driver.findElement(locatorInputFamilia).sendKeys(Familia);
    }

    // Ввод в поле Адрес
    public void sendAddress(String address) {
        driver.findElement(locatorInputAddress).sendKeys(address);
    }

    // выбор в поле метро
    public void sendMetro(String Metro) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        driver.findElement(locatorInputMetro).click();
        driver.findElement(locatorInputMetro).sendKeys(Metro);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locatorStationMetro));
        driver.findElement(locatorStationMetroInList).click();

    }

    // Ввод Телефона
    public void sendTelephone(String telephone) {
        driver.findElement(locatorInputTelephone).sendKeys(telephone);
    }

    // Клик на кнопку Далее
    public void clickButtonDalee() {
        driver.findElement(locatorButtonDalee).click();
    }

    // Ввод даты через ДД.ММ.ГГГГ
    public void sendDataAndEnter(String data) {

        driver.findElement(locatorInputWhen).sendKeys(data);
        driver.findElement(locatorInputWhen).sendKeys(Keys.ENTER);
    }

    // Выбор срока аренды
    public void clickRentalPeriod(String dey) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        driver.findElement(locatorRentalPeriod).click();
        switch (dey) {
            case "сутки":
            case "двое суток":
            case "трое суток":
            case "четверо суток":
            case "пятеро суток":
            case "шестеро суток":
            case "семеро суток":
                wait.until(ExpectedConditions.visibilityOfElementLocated(locatorMenuRentalPeriod));
                WebElement element = driver.findElement((By.xpath(".//div[text()='" + dey + "']")));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
                driver.findElement(By.xpath(".//div[text()='" + dey + "']")).click();
                break;
        }
    }

    // Выбор самоката, по стандарту всегда серый.
    public void clickColorScooter(String color) {

        if (color.equals("Черный")) {
            driver.findElement(locatorBlackScooter).click();
        } else {
            driver.findElement(locatorGreyScooter).click();
        }

    }

    // Клик по кнопке Заказать
    public void clickButtonZakaz() {
        driver.findElement(locatorButtonZakaz).click();
    }


    // Клик по кнопке ДА
    public void clickButtonDa() {
        driver.findElement(locatorButtonDa).click();
    }


    // Проверка наличия кнопки Статуса заказа на экране
    public boolean truOrFalseButtonStatusOrder() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locatorButtonStatusOrder));
        WebElement buttonStatusOrder = driver.findElement(locatorButtonStatusOrder);

        return buttonStatusOrder.isDisplayed();
    }

    //Загрузка Хедера формы заказ
    public static void waitHederOrderForms(WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locatorHederOrderForm));
    }
}
