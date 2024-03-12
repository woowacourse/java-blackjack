package blackjack;

import blackjack.machine.BlackjackMachine;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackApplication {

    public static void main(String[] args) {
        try {
            BlackjackMachine machine = new BlackjackMachine(new InputView(), new OutputView());
            machine.run();
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
