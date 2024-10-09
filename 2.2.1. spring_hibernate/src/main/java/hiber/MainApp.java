package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        Car lada = new Car("Копейка", 2101);
        Car bmv = new Car("БМВ", 525);
        Car mazda = new Car("Мазда", 3);
        Car toyota = new Car("Морковник", 2);


        userService.add(new User("User1", "Lastname1", "user1@mail.ru", lada));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", bmv));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", mazda));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", toyota));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCars());
            System.out.println();
        }
        System.out.println();
        User user = userService.getUserFromCarModelSeries("Морковник", 2);

        System.out.println("Пользователь - First Name " + user.getFirstName()
                + " - Владеет машиной - Модель " + user.getCars().getModel()
                + " " + "Серия " + user.getCars().getSeries());

        context.close();
    }
}
