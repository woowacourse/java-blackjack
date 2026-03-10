package blackjack;

import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        BlackjackRunner blackjackRunner = new BlackjackRunner(inputView, outputView);
        blackjackRunner.execute();
    }

}
