package co.com.Platzi.PrimerosPasos.component;

import org.springframework.stereotype.Component;

@Component
public class ComponentImplement implements ComponentDependency {

    @Override
    public void saludar() {
        System.out.println("Hola mundo desde mi componente. Soy Cristian Vargas");
    }
}
