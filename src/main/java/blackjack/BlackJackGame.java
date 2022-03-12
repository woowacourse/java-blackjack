package blackjack;

import blackjack.domain.BlackJackBoard;
import blackjack.domain.HitCommand;
import blackjack.dto.ParticipantCards;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackGame {

    private final BlackJackBoard blackJackBoard;

    public BlackJackGame() {
        this.blackJackBoard = createBlackJackBoard();
    }

    public void run() {
        printFirstHitCard();
        runAllPlayerTurn();
        printAllResults();
    }

    private BlackJackBoard createBlackJackBoard() {
        final List<String> playerNames = InputView.inputPlayerNames();
        return BlackJackBoard.createGame(playerNames);
    }

    private void printFirstHitCard() {
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
        final HitCommand hitCommand = inputHitCommand();
        final ParticipantCards currentPlayerCards = blackJackBoard.hitCurrentPlayer(hitCommand);
        OutputView.printPlayerCards(currentPlayerCards);
        runPlayerTurn();
    }

    private HitCommand inputHitCommand() {
        return HitCommand.from(InputView.inputHitCommand(blackJackBoard.getCurrentTurnPlayerName()));
    }

    private void runDealerTurn() {
        if (blackJackBoard.isDealerTurnEnd()) {
            return;
        }
        blackJackBoard.hitDealer();
        OutputView.printDealerHit();
        runDealerTurn();
    }

    private void printAllResults() {
        OutputView.printPlayerScoreResults(blackJackBoard.allPlayerScoreResults());
        OutputView.printAllOutcomeResult(blackJackBoard.calculateAllResults());
    }
}
