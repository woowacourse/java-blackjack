package blackjack;

import blackjack.controller.BlackjackController;

public class BlackjackMain {

    public static void main(String[] args) {
        BlackjackController blackjackController = new BlackjackController();
        blackjackController.playGame();
    }
}
