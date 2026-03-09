package blackjack;

import blackjack.domain.deck.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.GameResults;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackGame {

    private final Dealer dealer;
    private final Players players;

    public BlackjackGame() {
        this.dealer = new Dealer(new Deck());
        this.players = createPlayers();
    }

    public void start() {
        dealer.dealInitialCards(players);
        printInitialDeal();
        processPlayersTurn();
        processDealerTurn();
        printResults();
    }

    private Players createPlayers() {
        final List<String> names = InputView.readPlayerNames();
        final List<Player> players = names.stream()
                .map(Player::new)
                .toList();
        return new Players(players);
    }

    private void printInitialDeal() {
        OutputView.printInitialDeal(players, dealer);
    }

    private void processPlayersTurn() {
        players.getPlayers().forEach(this::processPlayerTurn);
    }

    private void processPlayerTurn(final Player player) {
        boolean hasHit = false;
        while (player.canReceiveCard()) {
            hasHit |= askHitAndProcess(player);
        }
        if (!hasHit) {
            OutputView.printPlayerCards(player);
        }
    }

    private boolean askHitAndProcess(final Player player) {
        final boolean wantsHit = InputView.readHitDecision(player.getName());
        if (!wantsHit) {
            player.stay();
            return false;
        }
        dealer.dealCardTo(player);
        OutputView.printPlayerCards(player);
        return true;
    }

    private void processDealerTurn() {
        while (dealer.canReceiveCard()) {
            dealer.drawAndReceive();
            OutputView.printDealerHit();
        }
    }

    private void printResults() {
        OutputView.printFinalCards(players, dealer);
        final GameResults gameResults = GameResults.calculate(players, dealer);
        OutputView.printFinalResults(gameResults);
    }
}
