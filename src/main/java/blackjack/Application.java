package blackjack;

import blackjack.model.BlackjackGame;
import blackjack.view.InputView;

public class Application {

    public static void main(String[] args) {
        BlackjackGame blackjackGame = new BlackjackGame(InputView.inputPlayerNames(), InputView::inputBet);
    }
}
