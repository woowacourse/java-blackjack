package blackjack;

import blackjack.domain.BlackJackBoard;
import blackjack.domain.DrawCommand;
import blackjack.dto.PlayerCards;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackGame {

    private BlackJackGame() {
        throw new AssertionError();
    }

    public static BlackJackBoard createBlackJackBoard() {
        final List<String> playerNames = InputView.inputPlayerNames();
        return BlackJackBoard.createGame(playerNames);
    }

    public static void printFirstDrawCard(final BlackJackBoard blackJackBoard) {
        OutputView.showPlayersFirstCards(blackJackBoard.getDealerFirstCard(), blackJackBoard.getPlayersFirstCards());
    }

    public static void runAllPlayerTurn(final BlackJackBoard blackJackBoard) {
        runPlayerTurn(blackJackBoard);
        runDealerTurn(blackJackBoard);
    }

    private static void runPlayerTurn(final BlackJackBoard blackJackBoard) {
        if (blackJackBoard.isPlayersTurnEnd()) {
            return;
        }
        final DrawCommand drawCommand = inputDrawCommand(blackJackBoard);
        final PlayerCards currentPlayerCards = blackJackBoard.drawCurrentPlayer(drawCommand);
        OutputView.printPlayerCards(currentPlayerCards);
        runPlayerTurn(blackJackBoard);
    }

    private static DrawCommand inputDrawCommand(final BlackJackBoard blackJackBoard) {
        return DrawCommand.from(InputView.inputDrawCommand(blackJackBoard.getCurrentTurnPlayerName()));
    }

    private static void runDealerTurn(final BlackJackBoard blackJackBoard) {
        if (blackJackBoard.isDealerTurnEnd()) {
            return;
        }
        blackJackBoard.drawDealer();
        OutputView.printDealerDraw();
        runDealerTurn(blackJackBoard);
    }

    public static void printAllResults(final BlackJackBoard blackJackBoard) {
        OutputView.printPlayerScoreResults(blackJackBoard.getPlayerScoreResults());
        OutputView.printAllOutcomeResult(blackJackBoard.calculateAllResults());
    }
}
