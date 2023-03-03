package balckjack;

import balckjack.controller.BlackJackController;
import balckjack.strategy.RandomCardPicker;

public class Application {

    public static void main(String[] args) {
        final RandomCardPicker picker = new RandomCardPicker();
        final BlackJackController blackJackController = new BlackJackController(picker);

        blackJackController.run();
    }
}
