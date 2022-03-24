package blackjack;

import blackjack.model.game.BlackjackGame;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {

    public static void main(String[] args) {
        BlackjackGame blackjackGame = new BlackjackGame(InputView.inputPlayerNames(), InputView::inputBet);
        blackjackGame.start();
        OutputView.printStartResult(blackjackGame.getDealer(), blackjackGame.getPlayers());
        blackjackGame.hitToPlayers(InputView::inputSign, OutputView::printPlayerTurnResult);
        OutputView.printDealerTurnResult(blackjackGame.getDealer());
        OutputView.printFinishResult(blackjackGame.getParticipant());
        OutputView.printBettingResult(blackjackGame.createBettingResult());
    }
}
