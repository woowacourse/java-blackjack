package blackjack;

import blackjack.controller.BlackJackController;
import blackjack.service.BlackJackService;
import blackjack.service.ShuffledCardsGenerator;

public class Application {
    public static void main(String[] args) {
        BlackJackService blackJackService = new BlackJackService(new ShuffledCardsGenerator());
        BlackJackController blackJackController = new BlackJackController(blackJackService);
        blackJackController.run();
    }
}
