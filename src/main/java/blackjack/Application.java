package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.domain.CardDeck;
import blackjack.domain.CardDump;
import blackjack.view.InputVIew;
import blackjack.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputVIew inputVIew = new InputVIew();
        OutputView outputView = new OutputView();
        CardDump cardDump = new CardDump();
        BlackjackController controller = new BlackjackController(inputVIew, outputView, cardDump);
        controller.run();
    }
}
