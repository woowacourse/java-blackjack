package blackjack;

import blackjack.controller.BlackjackController;

public class Application {

    public static void main(String[] args) {
        BlackjackController blackjackController = new BlackjackController();
        runApplication(blackjackController);
    }

    private static void runApplication(BlackjackController blackjackController) {
        try {
            runBlackjack(blackjackController);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            runApplication(blackjackController);
        }
    }

    private static void runBlackjack(BlackjackController blackjackController) {
        blackjackController.run();
    }
}
