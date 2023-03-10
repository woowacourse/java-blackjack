package blackjack;

import blackjack.controller.BlackJackController;
import blackjack.domain.card.ShuffledDeckGenerator;

public class Application {
    public static void main(String[] args) {
        BlackJackController blackJackController = new BlackJackController();
        blackJackController.run(new ShuffledDeckGenerator());
    }
}
