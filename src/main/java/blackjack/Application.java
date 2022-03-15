package blackjack;

import blackjack.model.BlackjackGame;
import blackjack.view.InputView;
import blackjack.view.ResultView;

public class Application {
    public static void main(String[] args) {
        BlackjackGame blackjackGame = new BlackjackGame(InputView.inputPlayerNames());

        blackjackGame = blackjackGame.start();
        ResultView.printStartResult(blackjackGame.getDealer(), blackjackGame.getPlayers());


        blackjackGame.hitOrStayUntilPossible(InputView::inputHitOrStaySign, ResultView::printCurrentTurnHitResult);

        ResultView.printFinalResult(blackjackGame.getDealer(), blackjackGame.getGamers());

        ResultView.printMatchResult(blackjackGame.createMatchResult());
    }
}
