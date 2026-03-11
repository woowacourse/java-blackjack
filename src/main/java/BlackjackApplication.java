import config.AppConfig;
import controller.BlackjackController;

public class BlackjackApplication {

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        BlackjackController blackjackController = appConfig.blackjackController();
        blackjackController.start();
    }
}
