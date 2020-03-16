package blackjack;

import blackjack.controller.BlackjackGameController;

public class BlackjackApplication {
    public static void main(String[] args) {
        //try {
            BlackjackGameController.run();
        //} catch (RuntimeException e) {
       //     System.out.println(e.getMessage());
        //}
    }
}
