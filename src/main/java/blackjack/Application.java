package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.view.InputView;
import blackjack.view.ResultView;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        final BlackjackController blackjackController = makeController();
        blackjackController.run();
    }

    private static BlackjackController makeController() {
        final InputView inputView = new InputView(new Scanner(System.in));
        final ResultView resultView = new ResultView();
        return new BlackjackController(inputView, resultView);
    }
}
