package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.controller.BlackjackProcessManager;
import blackjack.factory.SingDeckGenerator;

public class Application {

    public static void main(String[] args) {
        BlackjackProcessManager blackjackProcessManager = new BlackjackProcessManager(new SingDeckGenerator());
        BlackjackController blackjackController = new BlackjackController(blackjackProcessManager);

        blackjackController.run();
    }
}
