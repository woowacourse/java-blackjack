package blackjack;

import blackjack.controller.BlackJackController;
import blackjack.service.BlackJackService;

public class Application {

    public static void main(String[] args) {
        BlackJackController blackJackController = new BlackJackController(new BlackJackService());
        blackJackController.run();
    }
}
