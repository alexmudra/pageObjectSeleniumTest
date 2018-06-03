import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class LoginPageTest {

    private WebDriver driver;
    private LoginPage loginPage;

    @Before
    public void setUp(){
        System.setProperty("webdriver.gecko.driver", "D:\\workspace\\geckodriver\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1300, 700));
        driver.get("https://github.com/login");
        loginPage = new LoginPage(driver);//створимо об'єкт класу логін паже і передамо туди драйвер
    }

    @Test
    public void loginWithEmptyCredsTest(){
        LoginPage newLoginPage = loginPage.loginWithInvalidCreds ("","");//створимо новий об'єкт класу і
        // вкажим пустий юзернейм і пустий пароль
        String error = newLoginPage.getErrorText();
        Assert.assertEquals("Incorrect username or password.", error);
    }

    @Test
    public void loginWithIncorrectCredsTest(){
        LoginPage newLoginPage =  loginPage.loginWithInvalidCreds("qweqewqwe","qweqweweqwe");
        String error = newLoginPage.getErrorText();
        Assert.assertEquals("Incorrect username or password.", error);
    }

    @Test
    public void createAccTest(){//перевірим що при кліку на лінк Create an account то попадем на нову сторінку
        SignUpPage signUpPage = loginPage.createAccount();
        String heading = signUpPage.getHeadingText();//запишемо помилку в перемінну
        Assert.assertEquals("Join GitHub", heading);//порівняємо надпис з heading тим що вказаний на сайті
    }

    @After
    public void tearDown(){//закриємо драйвер (браузер)
        driver.quit();
    }
}
