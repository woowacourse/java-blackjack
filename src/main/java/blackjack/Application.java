package blackjack;

import blackjack.model.BlackjackGame;
import blackjack.view.InputView;
import blackjack.view.ResultView;

public class Application {
    public static void main(String[] args) {
        BlackjackGame blackjackGame = new BlackjackGame(InputView.inputPlayerNames());
        ResultView.printStartResult(blackjackGame.start());
        blackjackGame.performEachTurn(InputView::inputHitOrStaySign, ResultView::printCurrentTurnResult);
        ResultView.printFinalResult(blackjackGame.getDealer(), blackjackGame.getGamers());
        //ResultView.printMatchResult(blackjackGame.createMatchResult());
    }
}