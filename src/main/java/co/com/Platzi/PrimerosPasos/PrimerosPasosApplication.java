package co.com.Platzi.PrimerosPasos;

import co.com.Platzi.PrimerosPasos.bean.MyBean;
import co.com.Platzi.PrimerosPasos.bean.MyBeanWithDependency;
import co.com.Platzi.PrimerosPasos.bean.MyBeanWithProperties;
import co.com.Platzi.PrimerosPasos.component.ComponentDependency;
import co.com.Platzi.PrimerosPasos.entity.User;
import co.com.Platzi.PrimerosPasos.pojo.UserPojo;
import co.com.Platzi.PrimerosPasos.repository.UserRepository;
import co.com.Platzi.PrimerosPasos.services.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class PrimerosPasosApplication implements CommandLineRunner {

    private final Log LOGGER = LogFactory.getLog(PrimerosPasosApplication.class);
    private final ComponentDependency componentDependency;
    private final MyBean myBean;
    private final MyBeanWithDependency myBeanWithDependency;
    private final MyBeanWithProperties myBeanWithProperties;
    @Autowired
    private UserPojo userPojo;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

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
    public void run(String... args) {
        //clasesAnteriores();
        //clasesAnteriores2
        saveUsersInDB();
        getInformationJpqlFromUser();
    }

    private void getInformationJpqlFromUser() {
        userRepository.findByBirthdayBetween(LocalDate.of(1990, 01, 01), LocalDate.of(2005, 12, 25)).stream()
                .forEach(user -> LOGGER.info("Usuarios entre la 2 fechas suministradas: " + user));

        userRepository.findByNameLikeOrderByIdDesc("%a%").stream()
                .forEach(user -> LOGGER.info("Usuario encontrado con like y ordenados: " + user));

        LOGGER.info(userRepository.getAllByBirthdayAndEmail(LocalDate.of(1999, 06, 10), "cristian@mail.com")
                .orElseThrow(() -> new RuntimeException("No se encontró el usuario")));
    }

    private void saveWithTransactionalError() {
        User test1 = new User("Test1", "test1@mail.com", LocalDate.of(1999, 10, 15));
        User test2 = new User("Test2", "test2@mail.com", LocalDate.of(2000, 11, 23));
        User test3 = new User("Test3", "test3@mail.com", LocalDate.of(2001, 02, 26));

        List<User> users = Arrays.asList(test1, test2, test3);

        userService.saveTransactional(users);
    }

    private void saveUsersInDB() {
        User user1 = new User("Cristian", "cristian@mail.com", LocalDate.of(1999, 06, 10));
        User user2 = new User("Valentina", "valentina@mail.com", LocalDate.of(2001, 9, 9));
        User user3 = new User("Diana", "diana@mail.com", LocalDate.of(1997, 03, 21));
        User user4 = new User("Carolina", "carolina@mail.com", LocalDate.of(1996, 07, 5));
        User user5 = new User("Dayanna", "dayanna@mail.com", LocalDate.of(1999, 04, 05));
        List<User> users = Arrays.asList(user1, user2, user3, user4, user5);
        users.forEach(userRepository::save);
    }

    public void clasesAnteriores2() {
        LOGGER.info("Usuario con el método findByUserEmail" +
                userRepository.findByUserEmail("cristian@mail.com").orElseThrow(
                        () -> new RuntimeException("No se ha encontrado el usuario con el mail suministrado")));
        userRepository.findAndSort("a", Sort.by("id").descending()).stream()
                .forEach(user -> LOGGER.info("User with method sort " + user));

        userRepository.findByName("Cristian");

        userRepository.findByNameLike("%a%").stream().forEach(user -> LOGGER.info("Usuario findByNameLike" + user));

        userRepository.findByNameOrEmail("", "%@mail.com%").stream().forEach(user -> LOGGER.info("Usuario findByNameOrEmail" + user));
    }

    public void clasesAnteriores() {
        componentDependency.saludar();
        myBean.print();
        myBeanWithDependency.printWithDependency();
        myBeanWithProperties.function();
        System.out.println(userPojo.getEmail() + " " + userPojo.getPassword());

        try {
            int value = 10 / 0;
            LOGGER.info("El valor de mi variable es: " + value);
        } catch (Exception e) {
            LOGGER.error("Este es un error al intentar dividir entre cero " + e.getMessage());
        }
    }
}
