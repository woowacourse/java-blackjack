package blackjack;

import blackjack.configuration.ApplicationConfiguration;
import blackjack.controller.BlackjackController;

public class Application {

    public static void main(String[] args) {
        ApplicationConfiguration configuration = new ApplicationConfiguration();
        BlackjackController blackJackController = new BlackjackController(configuration);
        blackJackController.run();
    }
}
