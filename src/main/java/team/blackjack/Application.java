package team.blackjack;

import team.blackjack.config.AppConfig;
import team.blackjack.controller.BlackJackController;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = AppConfig.getInstance();
        BlackJackController blackJackController = appConfig.blackJackController();
        blackJackController.run();
    }
}
