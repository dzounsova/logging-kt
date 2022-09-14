package info.interventure.coffeemachine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoffeeMachineApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoffeeMachineApplication.class, args);

        //log();
    }

    private static void log() {
        //Logs message without context in console
        System.out.println("Presentation started");

        try {
            throwException();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private static void throwException() throws Exception {
        throw new Exception("Something went wrong");
    }
}
