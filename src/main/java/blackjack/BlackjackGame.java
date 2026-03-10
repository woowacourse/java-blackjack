package blackjack;

import blackjack.domain.betting.BettingMoney;
import blackjack.domain.deck.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.GameResults;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.stream.IntStream;

public class BlackjackGame {

    private final Dealer dealer;
    private final Players players;

    public BlackjackGame() {
        this.dealer = new Dealer(new Deck());
        this.players = createPlayers();
    }

    private Players createPlayers() {
        final List<String> names = InputView.readPlayerNames();
        final List<BettingMoney> bettingMonies = InputView.readBettingMonies(names);
        final List<Player> players = IntStream.range(0, names.size())
                .mapToObj(i -> new Player(names.get(i), bettingMonies.get(i)))
                .toList();
        return new Players(players);
    }

    public void start() {
        dealer.dealInitialCards(players);
        printInitialDeal();
        processPlayersTurn();
        processDealerTurn();
        printResults();
    }

    private void printInitialDeal() {
        OutputView.printInitialDeal(players, dealer);
    }

    private void processPlayersTurn() {
        players.getPlayers().forEach(this::processPlayerTurn);
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
            player.stay();
            return;
        }
        dealer.dealCardTo(player);
        OutputView.printPlayerCards(player);
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
        OutputView.printFinalProfits(gameResults, dealer);
    }
}
