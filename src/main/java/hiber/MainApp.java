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



      userService.add(new User("egor", "brows", "wow@wow.wow", new Car("lada", 2109)));
      userService.add(new User("max", "cat", "cat@cat.cat", new Car("nissan", 9000)));
      userService.add(new User("sven", "cat", "fluffy@fluffy.fluffy", new Car("bmw", 5)));
      userService.add(new User("daria", "ice", "ice@ice.ice", new Car("ford", 999)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar().getModel() + " " + user.getCar().getSeries());
         System.out.println();
      }

      User user = userService.getUserByCar("lada", 2109);
      System.out.println(user);
      context.close();
   }
}
