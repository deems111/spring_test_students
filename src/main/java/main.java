import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.TestImpl;

public class main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        TestImpl test = context.getBean(TestImpl.class);
        test.test();
    }
}
