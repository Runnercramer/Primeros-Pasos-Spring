package co.com.Platzi.PrimerosPasos.configuration;

import co.com.Platzi.PrimerosPasos.bean.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfigBean {

    @Bean
    public MyBean beanPruebas() {
        return new MyBeanImplement();
    }

    @Bean
    public MyOperation beanOperation() {
        return new MyOperationImplementation();
    }

    @Bean
    public MyBeanWithDependency beanOperation2(MyOperation myOperation) {
        return new MyBeanWithDependencyImplementation(myOperation);
    }

}
