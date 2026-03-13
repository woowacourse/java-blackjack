package blackjack;

import blackjack.domain.participant.Player;
import blackjack.domain.result.GameResults;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackGame {

    private final BlackjackBoard board;

    public BlackjackGame(final BlackjackBoard board) {
        this.board = board;
    }

    public void start() {
        board.dealInitialCards();
        OutputView.printInitialDeal(board.getPlayers(), board.getDealer());
        processPlayersTurn();
        processDealerTurn();
        printResults();
    }

    private void processPlayersTurn() {
        board.getPlayers()
                .forEach(this::processPlayerTurn);
    }

    private void processPlayerTurn(final Player player) {
        while (player.canReceiveCard()) {
            askHitAndProcess(player);
        }
        if (!player.hasAdditionalCard()) {
            OutputView.printPlayerCards(player);
        }
    }

    private void askHitAndProcess(final Player player) {
        final boolean wantsHit = InputView.readHitDecision(player.getName());
        if (!wantsHit) {
            board.stayPlayer(player);
            return;
        }
        board.hitPlayer(player);
        OutputView.printPlayerCards(player);
    }

    private void processDealerTurn() {
        while (board.dealerCanReceiveCard()) {
            board.hitDealer();
            OutputView.printDealerHit();
        }
    }

    private void printResults() {
        OutputView.printFinalCards(board.getPlayers(), board.getDealer());
        final GameResults gameResults = board.calculateResults();
        OutputView.printFinalProfits(gameResults, board.getDealer());
    }
}
