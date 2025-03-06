import config.Config;
import controller.BlackJackController;

public class Application {
    public static void main(String[] args) {
        Config config = new Config();
        BlackJackController blackJackController = config.blackJackController();
        blackJackController.run();
    }
}
