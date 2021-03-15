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
        } catch (NumberFormatException e) {
            System.out.println("잘못된 숫자 입력입니다.");
            runApplication(blackjackController);
        } catch (IllegalArgumentException e) {
            System.out.println("\n" + e.getMessage());
            runApplication(blackjackController);
        }
    }
}
