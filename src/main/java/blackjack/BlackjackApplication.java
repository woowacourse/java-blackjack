package blackjack;

import blackjack.controller.BlackjackGameController;

public class BlackjackApplication {
    public static void main(String[] args) {
        BlackjackGameController blackjackGameController = new BlackjackGameController();
        blackjackGameController.start();
    }
}