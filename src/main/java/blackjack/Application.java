package blackjack;

import blackjack.domain.BlackJackBoard;
import blackjack.domain.DrawCommand;
import blackjack.dto.PlayerCards;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class Application {

    public static void main(final String[] args) {
        final List<String> playerNames = InputView.inputPlayerNames();
        final BlackJackBoard blackJackBoard = BlackJackBoard.createGame(playerNames);
        OutputView.showPlayersFirstCards(blackJackBoard.getDealerFirstCard(), blackJackBoard.getPlayersFirstCards());

        runPlayerTurn(blackJackBoard);
        runDealerTurn(blackJackBoard);

        OutputView.printPlayerScoreResults(blackJackBoard.getPlayerScoreResults());
        OutputView.printAllOutcomeResult(blackJackBoard.calculateAllResults());
    }

    private static void runPlayerTurn(final BlackJackBoard blackJackBoard) {
        while (!blackJackBoard.isPlayersTurnEnd()) {
            final DrawCommand drawCommand = DrawCommand
                    .from(InputView.inputDrawCommand(blackJackBoard.getCurrentTurnPlayerName()));
            final PlayerCards currentPlayerCards = blackJackBoard.drawCurrentPlayer(drawCommand);
            OutputView.printPlayerCards(currentPlayerCards);
        }
    }

    private static void runDealerTurn(final BlackJackBoard blackJackBoard) {
        while (!blackJackBoard.isDealerTurnEnd()) {
            blackJackBoard.drawDealer();
            OutputView.printDealerDraw();
        }
    }
}
