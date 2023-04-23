package co.com.Platzi.PrimerosPasos.bean;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

public class MyBeanWithDependencyImplementation implements MyBeanWithDependency {

    private final Log LOGGER = LogFactory.getLog(MyBeanWithDependencyImplementation.class);
    private final MyOperation myOperation;

    public MyBeanWithDependencyImplementation(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {

        LOGGER.info("Se está ejecutando este método {} ");
        int numero = 17;
        LOGGER.debug("El número enviado como parámetro a la dependencia es: " + numero);

        System.out.println(myOperation.sum(numero));
        System.out.println("Hola desde la implementación de un @Bean con dependencia");
    }
}
