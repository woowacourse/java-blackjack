package blackjack;

import blackjack.controller.BlackjackGameController;

public class BlackjackApplication {
    public static void main(String[] args) {
        while (true) {
            try {
                BlackjackGameController.run();
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
