package blackjack;

import blackjack.machine.BlackjackMachine;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackApplication {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        try {
            BlackjackMachine machine = new BlackjackMachine(inputView, outputView);
            machine.run();
        } catch (IllegalArgumentException | IllegalStateException e) {
            outputView.printErrorMessage(e.getMessage());
        }
    }
}
