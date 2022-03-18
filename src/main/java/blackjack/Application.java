package blackjack;

import blackjack.model.BlackjackGame;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {

    public static void main(String[] args) {
        BlackjackGame blackjackGame = new BlackjackGame(InputView.inputPlayerNames(), InputView::inputBet);
        blackjackGame.start();
        OutputView.printStartResult(blackjackGame.getParticipants());
    }
}
