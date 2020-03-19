package second;

import second.domain.BlackJackGame;
import second.domain.answer.PlayerDecisions;
import second.view.InputView;
import second.view.OutputView;


public class BlackJackApplication {
    public static void main(String[] args) {
        BlackJackGame blackJackGame = new BlackJackGame(InputView.inputPlayer());

        blackJackGame.drawFirstPhase();
        OutputView.printInitialCards(blackJackGame.getPlayers(), blackJackGame.getDealer());

        final PlayerDecisions playerDecisions = new PlayerDecisions(InputView::choose, OutputView::printGamerCards);
        blackJackGame.doDrawMorePhase(playerDecisions);

        printResults(blackJackGame);
    }

    private static void printResults(BlackJackGame blackJackGame) {
        OutputView.printScore(blackJackGame);
        OutputView.printResults(blackJackGame.calculateResults());
    }
}
