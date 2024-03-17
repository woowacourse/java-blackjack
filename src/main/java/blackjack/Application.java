package blackjack;

import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {
    public static void main(String[] args) {
        BlackjackController blackjackController = new BlackjackController(new InputView(), new OutputView());
        blackjackController.play();
    }
}
