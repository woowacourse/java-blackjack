package blackjack;

import blackjack.view.InputView;
import blackjack.view.MessageResolver;
import blackjack.view.OutputView;

public class BlackjackApplication {
    public static void main(String[] args) {
        final InputView inputView = new InputView();
        final OutputView outputView = new OutputView(new MessageResolver());

        final BlackjackController blackjackController = new BlackjackController(inputView, outputView);
        blackjackController.run();
    }
}
