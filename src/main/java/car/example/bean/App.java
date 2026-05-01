package car.example.bean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.bean.Context.xml");
        // use the context here
        //((ClassPathXmlApplicationContext) context).close();
        Mybean mybean = (Mybean) context.getBean("mybean");
        System.out.println(mybean);


    }
}

