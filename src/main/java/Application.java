import config.AppConfig;
import controller.BlackJackController;

public class Application {

    public static void main(String[] args) {
        blackjackController().run();
    }

    private static BlackJackController blackjackController() {
        AppConfig config = new AppConfig();
        return config.blackJackController();
    }
}

