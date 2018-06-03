import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class MainPageTest {

    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp(){
        System.setProperty("webdriver.gecko.driver", "D:\\workspace\\geckodriver\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1300, 700));
        driver.get("http://github.com");
        mainPage = new MainPage(driver);//створимо об'єкт Main
    }

    @Test
    //Перевіримо чи працює кнопка Sign In
    public void signInTest(){
        LoginPage loginPage = mainPage.clickSignIn();//створюємо об'єкт класу логінпейж і визиваємо метод clickSignIn на цій сторінці
        String heading = loginPage.getHeadingText();//перевіримо чи присутній заголовок на сторінці
        Assert.assertEquals("Sign in to GitHub", heading);//перевіримо за допомогою асерта чи назва співпадає з
    }

    @Test
    public void registerFailTest(){
        SignUpPage signUpPage = mainPage.register("testuser", "testemail", "testpass"); //створимо сторінку SignUpPage і скористаємось
        //методом register
        String error = signUpPage.getMainErrorText();//перемінна ерор для порівняння тексту помилки
        Assert.assertEquals("There were problems creating your account.", error);
    }

    @Test
    public void signUpEmptyUsernameTest(){
        SignUpPage signUpPage = mainPage.register("", "mail", "pass");//створимо об'єкт класу Sign Up Page і виконаємо в
        //MainPage class метод під назвою resicter
        String error = signUpPage.getUsernameErrorText();//стоворимо нову перемінну error і визвемо метод щоб отримати текст помилки у поля
        Assert.assertEquals("Login can't be blank", error);//перевіримо що тест з "Login can't be blank" однаковий з помилкою
    }

    @Test//тест для перевірки поведінки системи при реєстрації з невалідними даними для емайла
    public void signUpInvalidEmailTest(){
        SignUpPage signUpPage = mainPage.register("qeqwe", "qweq", "pass");
        String error = signUpPage.getEmailErrorText();
        Assert.assertEquals("Email is invalid or already taken", error);
    }

    @After
   public void tearDown(){
     driver.quit();
   }
}
