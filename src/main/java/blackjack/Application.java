package blackjack;

import blackjack.controller.BlackJackController;
import blackjack.strategy.RandomCardPicker;

public class Application {

    public static void main(String[] args) {
        final RandomCardPicker picker = new RandomCardPicker();
        final BlackJackController blackJackController = new BlackJackController();

        blackJackController.run(picker);
    }
}
