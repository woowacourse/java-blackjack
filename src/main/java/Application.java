import config.DIConfig;
import controller.BlackJackController;

public class Application {
    public static void main(String[] args) {
        DIConfig diConfig = new DIConfig();
        BlackJackController blackJackController = diConfig.blackJackController();
        blackJackController.run();
    }
}
