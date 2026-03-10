package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.utils.Console;

public class Application {

    public static void main(String[] args) {
        final BlackjackController blackjackController = new BlackjackController();
        blackjackController.run();

        Console.close();
    }

}
