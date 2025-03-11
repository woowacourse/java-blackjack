package blackjack;

import blackjack.configuration.ApplicationConfiguration;
import blackjack.controller.BlackJackController;

public class Application {

    public static void main(String[] args) {
        ApplicationConfiguration configuration = new ApplicationConfiguration();
        BlackJackController blackJackController = new BlackJackController(configuration);
        blackJackController.run();
    }
}
