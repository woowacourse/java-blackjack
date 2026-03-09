package blackjack;

import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackApplication {

    public static void main(final String[] args) {
        final BlackjackController controller = new BlackjackController(new InputView(), new OutputView());
        controller.run();
    }
}
