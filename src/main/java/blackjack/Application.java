package blackjack;

import blackjack.model.BlackJackGame;
import blackjack.model.MatchResult;
import blackjack.view.InputView;
import blackjack.view.ResultView;

public class Application {
    public static void main(String[] args) {
        BlackJackGame blackJackGame = new BlackJackGame(InputView.inputPlayerNames());

        blackJackGame.start();
        ResultView.printStartResult(blackJackGame.getDealer(), blackJackGame.getGamers());

        blackJackGame.hitOrStayUntilPossible(InputView::inputHitOrStaySign, ResultView::printCurrentTurnHitResult);

        ResultView.printFinalResult(blackJackGame.getDealer(), blackJackGame.getGamers());

        ResultView.printMatchResult(blackJackGame.createMatchResult());
    }
}
