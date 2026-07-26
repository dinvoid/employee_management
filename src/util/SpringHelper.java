package util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringHelper {

    private static ApplicationContext appctx =
            new ClassPathXmlApplicationContext(
                    "applicationContext.xml");

    public static ApplicationContext getAppctx() {
        return appctx;
    }

}