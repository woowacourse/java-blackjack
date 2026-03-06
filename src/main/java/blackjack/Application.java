package blackjack;

import blackjack.config.AppConfig;
import blackjack.controller.BlackjackController;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        BlackjackController controller = appConfig.blackjackController();
        controller.run();
    }
}
