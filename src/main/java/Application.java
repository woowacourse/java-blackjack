import config.BlackJackConfig;
import controller.BlackJackController;

public class Application {

    public static void main(String[] args) {
        BlackJackController blackJackController = BlackJackConfig.createBlackJackController();
        blackJackController.start();
    }
}
