package blackjack;

import blackjack.domain.BlackJackBoard;
import blackjack.domain.DrawCommand;
import blackjack.dto.PlayerCards;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackGame {

    private final BlackJackBoard blackJackBoard;

    public BlackJackGame() {
        this.blackJackBoard = createBlackJackBoard();
    }

    public void run() {
        printFirstDrawCard();
        runAllPlayerTurn();
        printAllResults();
    }

    private BlackJackBoard createBlackJackBoard() {
        final List<String> playerNames = InputView.inputPlayerNames();
        return BlackJackBoard.createGame(playerNames);
    }

    private void printFirstDrawCard() {
        OutputView.showPlayersFirstCards(blackJackBoard.getDealerFirstCard(), blackJackBoard.getPlayersFirstCards());
    }

    private void runAllPlayerTurn() {
        runPlayerTurn();
        runDealerTurn();
    }

    private void runPlayerTurn() {
        if (blackJackBoard.isPlayersTurnEnd()) {
            return;
        }
        final DrawCommand drawCommand = inputDrawCommand();
        final PlayerCards currentPlayerCards = blackJackBoard.drawCurrentPlayer(drawCommand);
        OutputView.printPlayerCards(currentPlayerCards);
        runPlayerTurn();
    }

    private DrawCommand inputDrawCommand() {
        return DrawCommand.from(InputView.inputDrawCommand(blackJackBoard.getCurrentTurnPlayerName()));
    }

    private void runDealerTurn() {
        if (blackJackBoard.isDealerTurnEnd()) {
            return;
        }
        blackJackBoard.drawDealer();
        OutputView.printDealerDraw();
        runDealerTurn();
    }

    private void printAllResults() {
        OutputView.printPlayerScoreResults(blackJackBoard.allPlayerScoreResults());
        OutputView.printAllOutcomeResult(blackJackBoard.calculateAllResults());
    }
}
