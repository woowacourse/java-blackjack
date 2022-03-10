package blackjack;

import blackjack.domain.BlackJackBoard;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class Application {

    public static void main(final String[] args) {
        final List<String> playerNames = InputView.inputPlayerNames();
        final BlackJackBoard blackJackBoard = BlackJackBoard.createGame(playerNames);
        OutputView.showPlayersFirstCards(blackJackBoard.getDealerFirstCard(), blackJackBoard.getPlayersFirstCards());

        BlackJackGame.runPlayerTurn(blackJackBoard);
        BlackJackGame.runDealerTurn(blackJackBoard);

        OutputView.printPlayerScoreResults(blackJackBoard.getPlayerScoreResults());
        OutputView.printAllOutcomeResult(blackJackBoard.calculateAllResults());
    }
}
