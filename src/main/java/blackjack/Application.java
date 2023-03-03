package blackjack;

import blackjack.controller.BackJackController;

public class Application {

    public static void main(String[] args) {
        BackJackController backJackController = new BackJackController();
        backJackController.run();
    }
}
