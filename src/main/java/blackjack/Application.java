package blackjack;

import blackjack.domain.BlackjackRunner;

public class Application {

    public static void main(String[] args) {
        BlackjackRunner blackjackController = new BlackjackRunner();
        blackjackController.run();
    }
}
