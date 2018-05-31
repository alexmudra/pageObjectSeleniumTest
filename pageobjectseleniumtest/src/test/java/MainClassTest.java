
//Цей клас буде мати багато тестових методів та тестів взагалі

import com.google.common.annotations.VisibleForTesting;
import org.junit.*;
import org.junit.BeforeClass;

public class MainClassTest {

//РОЗКЛЯДАЄМО АНОТАЦІЇ JUNIT
    @BeforeClass
    public void beforeClassNethod(){ //цей метод буде виконуватись першим, дозволяє нам підготувати тестове середовище
        //використанням
    }

    @Before
    public void setUp(){ //дозволяє вказати метод який буде запускатися перед кожним тестовим методом
    }

    @After
    public void tearDown(){ //дозволяє вказати метод який буде запускатися після кожного тестового методу
    }

    @Test //сама головна анотація в JNit. Ця анотація і є тестовим методом
    public void method1(){
    }

    @Test //сама головна анотація в JNit
    public void method2(){
    }

    @Test //сама головна анотація в JNit
    @Ignore // ця анотація блокує запускання тестового методу
    public void method3(){
    }

    @AfterClass
        public void afterClassMethod(){ //цей метод який виконається після виконання всіх тестів
        }


}
