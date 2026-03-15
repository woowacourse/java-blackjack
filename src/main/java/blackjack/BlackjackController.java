package blackjack;

import blackjack.domain.deck.ShuffleStrategy;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.Collections;
import java.util.List;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        run(Collections::shuffle);
    }

    public void run(final ShuffleStrategy strategy) {
        final List<String> names = inputView.readPlayerNames();
        final BlackjackGame game = new BlackjackGame(names, strategy);

        placeWagers(game);
        dealAndPrintInitialCards(game);
        processAllPlayersTurn(game);
        processDealerTurn(game);

        printGameResults(game);
        printProfitResults(game);
    }

    private void placeWagers(final BlackjackGame game) {
        for (String name : game.getPlayerNames()) {
            int amount = inputView.readWager(name);
            game.placeWager(name, amount);
        }
    }

    private void dealAndPrintInitialCards(final BlackjackGame game) {
        outputView.printInitialDeal(game.dealInitialCards());
    }

    private void processAllPlayersTurn(final BlackjackGame game) {
        for (String name : game.getPlayerNames()) {
            processPlayerTurn(game, name);
        }
    }

    private void processPlayerTurn(final BlackjackGame game, final String playerName) {
        boolean hasHit = false;
        while (game.canPlayerHit(playerName) && inputView.readHitDecision(playerName)) {
            outputView.printPlayerCards(game.hitPlayer(playerName));
            hasHit = true;
        }
        if (!hasHit) {
            outputView.printPlayerCards(game.getPlayerCards(playerName));
        }
    }

    private void processDealerTurn(final BlackjackGame game) {
        while (game.canDealerHit()) {
            game.hitDealer();
            outputView.printDealerHit();
        }
    }

    private void printGameResults(final BlackjackGame game) {
        outputView.printFinalCards(game.getFinalCards());
        outputView.printFinalResults(game.resolveResults());
    }

    private void printProfitResults(final BlackjackGame game) {
        outputView.printDealerProfit(game.calculateDealerProfit());
        outputView.printPlayerProfits(game.calculatePlayerProfits());
    }
}
