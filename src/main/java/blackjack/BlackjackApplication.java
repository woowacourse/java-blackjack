package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.Scanner;

public class BlackjackApplication {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final InputView inputView = new InputView(scanner);
        final OutputView outputView = new OutputView();
        final BlackjackController blackjackController = new BlackjackController(inputView, outputView);

        try {
            blackjackController.run();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
        }
    }
}
