import config.AppConfig;
import controller.BlackJackController;

public class Application {

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        BlackJackController blackJackController = appConfig.blackJackController();

        blackJackController.start();
    }
}
