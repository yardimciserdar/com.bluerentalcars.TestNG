package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BluerentalcarsPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.time.Duration;

public class KullaniciOlusturma {
    @Test
    public void test01(){

        BluerentalcarsPage bluerentalcarsPage = new BluerentalcarsPage();

        // 1-Anasayfaya git
        Driver.getDriver().get(ConfigReader.getProperty("bluerentalcarsUrl"));

        // 2-Anasayfadaki login tuşuna bas
        bluerentalcarsPage.ilkLogin.click();

        // 3-Create new user butonuna bas
        bluerentalcarsPage.createNewUserElement.click();


        // 4-"Register" yazısının çıktığını test ediniz
        Assert.assertTrue(bluerentalcarsPage.registerYazisiElement.isDisplayed());

        // 5-Gerekli bilgileri doldurduktan sonra Register butonuna basınız.
        Actions actions = new Actions(Driver.getDriver());
        Faker faker = new Faker();
        String emailAdress = faker.internet().emailAddress();
        String password = faker.internet().password();
        String firsName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String expectedName = firsName + " " + lastName;
        System.out.println("email adres : " + emailAdress);
        System.out.println("şifre : " + password);

        actions.click(bluerentalcarsPage.firstNameKutusuElement)
                .sendKeys(firsName + Keys.TAB)
                .sendKeys(lastName + Keys.TAB)
                .sendKeys(faker.phoneNumber().phoneNumber())
                .sendKeys(faker.address().fullAddress()+ Keys.TAB)
                .sendKeys(faker.address().zipCode() + Keys.TAB)
                .sendKeys(emailAdress + Keys.TAB)
                .sendKeys(password + Keys.TAB)
                .sendKeys(password + Keys.TAB)
                .sendKeys(Keys.PAGE_DOWN)
                .perform();

          ReusableMethods.waitForClickablility(bluerentalcarsPage.registerButtonElement,10);

         // ReusableMethods.waitFor(3);


        // 6-"You are registered successfully" yazısını gördüğünüzü test ediniz

        ReusableMethods.waitForVisibility(bluerentalcarsPage.basariliGirisYazisiElement,5);
        Assert.assertTrue(bluerentalcarsPage.basariliGirisYazisiElement.isDisplayed());

        // 7-Login sayfasındakı Login tuşuna basınız
        bluerentalcarsPage.emailKutusu.sendKeys(emailAdress);
        bluerentalcarsPage.passwordKutusu.sendKeys(password);
        // 8-Doğru şekilde siteye giriş yaptığınızı test ediniz.
        String actualName  = bluerentalcarsPage.profilIsmi.getText();
        System.out.println(actualName);
        Assert.assertEquals(actualName,expectedName);
        // 9-Sayfayı kapatınız.
        Driver.closeDriver();
    }
}
