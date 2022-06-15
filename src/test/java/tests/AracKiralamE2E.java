package tests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BluerentalcarsPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;

import javax.swing.*;
import java.security.Key;

public class AracKiralamE2E extends TestBaseRapor {
    BluerentalcarsPage bluerentalcarsPage = new BluerentalcarsPage();
@Test

public void test01 () {
    extentTest = extentReports.createTest("Pozitif Login Test" , "Kullanıcı dogru maili ve şifre ile giriş yapabilmeli ");


    //1- kullanici "https://www.bluerentalcars.com/" sitasine gider
    Driver.getDriver().get("bluerentalcarsUrl");
    extentTest.info("siteye giriş yapıldı");

    //2- anasayfadaki login butonuna bas
    bluerentalcarsPage.ilkLogin.click();
    extentTest.info("anasayfadaki logine tıklandı");


//3- login aşaasında email adress kısmına doğru maili gir "customer@bluerentalcars.com"
    bluerentalcarsPage.emailKutusu.sendKeys(ConfigReader.getProperty("brcValidEmail"));
    extentTest.info("doğrı mail mail girişi yapıldı");


//4- login aşamasında password kısmına doğru şifreyi gir "12345"
    bluerentalcarsPage.passwordKutusu.sendKeys("brcValidPassword");
    extentTest.info("doğru şifre girişi yapıldı ");

//5 -email ve şifreden sonra giriş yapabilmek için alt kısımdaki  login butonu ile giriş yap
    bluerentalcarsPage.ikinciLogin.click();
    extentTest.info("ikinci login tıklandı");


//6- giriş yaptıktan sonra şifre için gelen pop'un  "kapat" bas
   // Driver.getDriver().switchTo().alert().dismiss();
//7- giriş yapıldıgını test etmek için  kullanınıcının isminin "Jordon Stark" oldugunu ontrol edelim
    String expectedUserName = "Jordon Stark";
    String actualUserName = bluerentalcarsPage.profilIsmi.getText();
    Assert.assertEquals(actualUserName,expectedUserName);
    extentTest.pass("Kullanıcı adı ana sayfada görüldü");

//8- kullanici herhangi bir arac secip gerekli bilgileri doldursun
    Select select = new Select(bluerentalcarsPage.ddm);
    select.selectByVisibleText("Ford Kuga");
    Actions actions = new Actions(Driver.getDriver());
    actions.click(bluerentalcarsPage.pickUp)
            .sendKeys("kansas Arkansas City" + Keys.TAB)
            .sendKeys("Kansas Arkansas City" + Keys.TAB)
            .sendKeys("17092022"+Keys.TAB)
            .sendKeys("1200"+Keys.TAB)
            .sendKeys("28092022"+Keys.TAB)
            .sendKeys("1200").perform();
    extentTest.info("Gerekli araç kiralama bilgileri girildi");


//9- kullanici continue rezervation butonuna tiklasin
    bluerentalcarsPage.completeReservationButon.click();
    extentTest.info("Devam butonuna basıldı");

//10- Complete Reservation basliginin gorundugunu test eder
    Assert.assertTrue(bluerentalcarsPage.completeReservationYazsi.isDisplayed());
    extentTest.pass("Başlık complete yazısı görüldü");


//11- Complete Reservation istenilen bilgiler doldurulur
    actions.click(bluerentalcarsPage.cardNo)
            .sendKeys("1234123412341234"+ Keys.TAB)
            .sendKeys("Jordon Stark"+Keys.TAB)
            .sendKeys("1224"+Keys.TAB)
            .sendKeys("122").perform();

    extentTest.info("Fatura bilgileri dolduruldu");

    bluerentalcarsPage.rodioButon.click();
    extentTest.info("Kullanıcı doldurduğu bilgilerin doğrulupunu onayladı");



//12- Reservasyon tamamlanir
    bluerentalcarsPage.completeReservationButon.click();
    extentTest.info("Kullanıcı rezervasyon işlemini başarı ile tamamladı");


//13. Rezervasyonun tamamlandigini test eder
    ReusableMethods.waitForVisibility(bluerentalcarsPage.aracKiralamaBasariliGirisMsj,10);
    Assert.assertTrue(bluerentalcarsPage.aracKiralamaBasariliGirisMsj.isDisplayed());
    extentTest.pass("kullanıcı başarılı bir şekilde araç kiraladı");


}


}
