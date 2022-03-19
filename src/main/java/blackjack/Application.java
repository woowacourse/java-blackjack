package blackjack;

import blackjack.model.game.BlackjackGame;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {

    public static void main(String[] args) {
        BlackjackGame blackjackGame = new BlackjackGame(InputView.inputPlayerNames(), InputView::inputBet);
        blackjackGame.start();
        OutputView.printStartResult(blackjackGame.getParticipants());
        blackjackGame.hitToParticipants(InputView::inputSign, OutputView::printParticipantCardsResult);
        OutputView.printParticipantsScoreResult(blackjackGame.getParticipants());
    }
}
