package co.com.Platzi.PrimerosPasos;

import co.com.Platzi.PrimerosPasos.bean.MyBean;
import co.com.Platzi.PrimerosPasos.bean.MyBeanWithDependency;
import co.com.Platzi.PrimerosPasos.bean.MyBeanWithProperties;
import co.com.Platzi.PrimerosPasos.component.ComponentDependency;
import co.com.Platzi.PrimerosPasos.pojo.UserPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PrimerosPasosApplication implements CommandLineRunner {

    private ComponentDependency componentDependency;
    private MyBean myBean;
    private MyBeanWithDependency myBeanWithDependency;
    private MyBeanWithProperties myBeanWithProperties;
    @Autowired
    private UserPojo userPojo;

    private final Log LOGGER = LogFactory.getLog(PrimerosPasosApplication.class);

    @Autowired
    public PrimerosPasosApplication(@Qualifier("componentImplement2") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties) {
        this.componentDependency = componentDependency;
        this.myBean = myBean;
        this.myBeanWithDependency = myBeanWithDependency;
        this.myBeanWithProperties = myBeanWithProperties;

    }

    public static void main(String[] args) {

        SpringApplication.run(PrimerosPasosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        componentDependency.saludar();
        myBean.print();
        myBeanWithDependency.printWithDependency();
        myBeanWithProperties.function();
        System.out.println(userPojo.getEmail() + " " + userPojo.getPassword());

        try{

        }catch(Exception e){
            LOGGER.error("Este es un error personalizado lanzado por el usuario");
        }
    }
}
