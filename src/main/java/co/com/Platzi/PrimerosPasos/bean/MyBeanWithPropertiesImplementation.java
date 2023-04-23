package co.com.Platzi.PrimerosPasos.bean;

public class MyBeanWithPropertiesImplementation implements MyBeanWithProperties {

    private String name;
    private String lastName;

    public MyBeanWithPropertiesImplementation(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    @Override
    public String function() {
        return name + " " + lastName;
    }
}
