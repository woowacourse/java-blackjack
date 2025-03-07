package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.domain.CardDump;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputVIew = new InputView();
        OutputView outputView = new OutputView();
        CardDump cardDump = new CardDump();
        BlackjackController controller = new BlackjackController(inputVIew, outputView, cardDump);
        controller.run();
    }
}
