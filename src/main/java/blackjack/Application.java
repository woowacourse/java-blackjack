package blackjack;

import blackjack.controller.BlackJackController;
import blackjack.exception.InvalidNameInputException;

public class Application {
    public static void main(String[] args) {
        BlackJackController.play();
    }
}
