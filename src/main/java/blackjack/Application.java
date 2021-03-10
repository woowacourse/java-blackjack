package blackjack;

import blackjack.controller.BlackjackController;

public class Application {

    public static void main(String[] args) {
        BlackjackController blackjackController = new BlackjackController();
        runApplication(blackjackController);
    }

    private static void runApplication(BlackjackController blackjackController) {
        try {
            blackjackController.run();
        } catch (IllegalArgumentException e) {
            System.out.println("\n" + e.getMessage());
            runApplication(blackjackController);
        }
    }
}
