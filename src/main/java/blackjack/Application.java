package blackjack;

import blackjack.controller.BlackJackController;
import blackjack.service.BlackJackService;
import blackjack.service.ShuffledDeckGenerator;

public class Application {
    public static void main(String[] args) {
        BlackJackService blackJackService = new BlackJackService();
        BlackJackController blackJackController = new BlackJackController(blackJackService);
        blackJackController.run(new ShuffledDeckGenerator());
    }
}
