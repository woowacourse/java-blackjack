package blackjack;

import blackjack.domain.BlackjackBoard;
import blackjack.domain.HitCommand;
import blackjack.dto.ParticipantCards;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackGame {

    private final BlackjackBoard blackjackBoard;

    public BlackjackGame() {
        this.blackjackBoard = createBlackjackBoard();
    }

    public void run() {
        printFirstHitCard();
        runAllPlayerTurn();
        printAllResults();
    }

    private BlackjackBoard createBlackjackBoard() {
        final List<String> playerNames = InputView.inputPlayerNames();
        return BlackjackBoard.createGame(playerNames);
    }

    private void printFirstHitCard() {
        OutputView.showPlayersFirstCards(blackjackBoard.getDealerFirstCard(), blackjackBoard.getPlayersFirstCards());
    }

    private void runAllPlayerTurn() {
        runPlayerTurn();
        runDealerTurn();
    }

    private void runPlayerTurn() {
        if (blackjackBoard.isPlayersTurnEnd()) {
            return;
        }
        final HitCommand hitCommand = inputHitCommand();
        final ParticipantCards currentPlayerCards = blackjackBoard.hitCurrentPlayer(hitCommand);
        OutputView.printPlayerCards(currentPlayerCards);
        runPlayerTurn();
    }

    private HitCommand inputHitCommand() {
        return HitCommand.from(InputView.inputHitCommand(blackjackBoard.getCurrentTurnPlayerName()));
    }

    private void runDealerTurn() {
        if (blackjackBoard.isDealerTurnEnd()) {
            return;
        }
        blackjackBoard.hitDealer();
        OutputView.printDealerHit();
        runDealerTurn();
    }

    private void printAllResults() {
        OutputView.printPlayerScoreResults(blackjackBoard.allPlayerScoreResults());
        OutputView.printAllOutcomeResult(blackjackBoard.calculateAllResults());
    }
}
