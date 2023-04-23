package co.com.Platzi.PrimerosPasos.bean;

public class MyBeanImplement implements MyBean {
    @Override
    public void print() {
        System.out.println("Hola, desde my Bean. Pruebas de inyección de dependencias");
    }
}
