import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class SignUpPageTest {

    WebDriver driver;
    SignUpPage signUpPage;//імпортували клас і створили свій дочірній signup page

    @Before
    public void setUp(){
        System.setProperty("webdriver.gecko.driver", "D:\\workspace\\geckodriver\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1300, 700));
        driver.get("https://github.com/join");//вкажимо адресу SingUp page
        signUpPage = new SignUpPage(driver);
    }

    @Test
    public void signUpWithShortPass(){//тест для перевірки поведінки форми з коротким паролем
        SignUpPage sp = signUpPage.typePassword("qwe");// створимо перемінну sp
        String error = sp.getPasswrodErrorText();//якщо отримаємо помилку то запишем її в перемінну стрінк error
        Assert.assertEquals("Password is too short (minimum is 7 characters) and needs at least one number", error); //перевіримо чому буде рівна помилка
    }

    @Test
    public void signUpReservedUsernameTest(){//перевірка зарезервованого (вже зареєстрованого) юзернейма
        SignUpPage sp = signUpPage.typeUserName("username");
        String error = sp.getUsernameErrorText();
        Assert.assertEquals("Username name 'username' is a reserved word", error);
    }

    @Test
    public void signUpTakenUsername(){//первіремо тест коли ми будемо реєструватися з username який вже зайнятий
        SignUpPage sp = signUpPage.typeUserName("user");
        String error = sp.getUsernameErrorText();
        Assert.assertEquals("Username is already taken", error);
    }

    @Test
    public void getHeadingTest(){ //тест коли ми відкриваємо нову сторінку то побачимо текст "Join GitHub" в хедері
        String heading = signUpPage.getHeadingText();
        Assert.assertEquals("Join GitHub", heading);
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
