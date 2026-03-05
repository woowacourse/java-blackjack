package blackjack;

import blackjack.domain.deck.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.GameResults;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final Deck deck = new Deck();
        final Players players = createPlayers();
        final Dealer dealer = new Dealer();

        dealInitialCards(deck, players, dealer);
        outputView.printInitialDeal(players, dealer);

        processPlayersTurn(deck, players);
        processDealerTurn(deck, dealer);

        printResults(players, dealer);
    }

    private Players createPlayers() {
        final List<String> names = inputView.readPlayerNames();
        final List<Player> playerList = names.stream()
                .map(Player::new)
                .toList();
        return new Players(playerList);
    }

    private void dealInitialCards(final Deck deck, final Players players, final Dealer dealer) {
        for (int i = 0; i < 2; i++) {
            players.getPlayers().forEach(player -> player.receiveCard(deck.draw()));
            dealer.receiveCard(deck.draw());
        }
    }

    private void processPlayersTurn(final Deck deck, final Players players) {
        players.getPlayers().forEach(player -> processPlayerTurn(deck, player));
    }

    private void processPlayerTurn(final Deck deck, final Player player) {
        boolean hasHit = false;
        while (player.canReceiveCard()) {
            if (!askHitAndProcess(deck, player)) {
                if (!hasHit) {
                    outputView.printPlayerCards(player);
                }
                return;
            }
            hasHit = true;
        }
    }

    private boolean askHitAndProcess(final Deck deck, final Player player) {
        final boolean wantsHit = inputView.readHitDecision(player.getName());
        if (!wantsHit) {
            player.stay();
            return false;
        }
        player.receiveCard(deck.draw());
        outputView.printPlayerCards(player);
        return true;
    }

    private void processDealerTurn(final Deck deck, final Dealer dealer) {
        while (dealer.canReceiveCard()) {
            dealer.receiveCard(deck.draw());
            outputView.printDealerHit();
        }
    }

    private void printResults(final Players players, final Dealer dealer) {
        outputView.printFinalCards(players, dealer);
        final GameResults gameResults = GameResults.calculate(players, dealer);
        outputView.printFinalResults(gameResults);
    }
}
