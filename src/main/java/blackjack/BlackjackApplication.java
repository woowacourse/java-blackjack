package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.Scanner;

public class BlackjackApplication {

    public static void main(String[] args) {
        final BlackjackController blackjackController = new BlackjackController(
                new InputView(new Scanner(System.in)),
                new OutputView()
        );
        blackjackController.run();
    }
}
