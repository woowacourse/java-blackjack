package blackjack;

import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackApplication {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        BlackjackMachine machine = new BlackjackMachine(inputView, outputView);
        machine.run();
    }
}
