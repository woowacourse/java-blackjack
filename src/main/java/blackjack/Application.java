package blackjack;

import blackjack.controller.BlackJackController;

public class Application {

    public void main(String[] args) {
        BlackJackController blackJackController = new BlackJackController();
        blackJackController.run();
    }
}
