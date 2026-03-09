package blackjack;

import blackjack.domain.deck.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.GameResults;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackjackController {

    private static final int INIT_ROUND = 2;

    private final InputView inputView;
    private final OutputView outputView;
    private final Map<Player, Boolean> hitDecision = new HashMap<>();

    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final Deck deck = new Deck();
        deck.shuffle();

        final Players players = createPlayers();
        final Dealer dealer = new Dealer();
        dealInitialCards(deck, players, dealer, INIT_ROUND);
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

        playerList.forEach(player -> hitDecision.put(player, true));

        return new Players(playerList);
    }

    private void dealInitialCards(final Deck deck, final Players players, final Dealer dealer, int initRound) {
        for (int i = 0; i < initRound; i++) {
            dealOneRound(deck, players, dealer);
        }
    }

    private void dealOneRound(final Deck deck, final Players players, final Dealer dealer) {
        players.players().forEach(player -> player.receiveCard(deck.draw()));
        dealer.receiveCard(deck.draw());
    }

    private void processPlayersTurn(final Deck deck, final Players players) {
        players.players().forEach(player -> processPlayerTurn(deck, player));
    }

    private void processPlayerTurn(final Deck deck, final Player player) {
        boolean hasHit = false;
        while (canProcess(player)) {
            final boolean hit = askHitAndProcess(deck, player);
            hasHit = hasHit || hit;
        }
        printCardsIfNeverHit(player, hasHit);
    }

    private boolean canProcess(Player player) {
        return hasHitDecision(player) && player.canReceiveCard();
    }

    private Boolean hasHitDecision(Player player) {
        return hitDecision.get(player);
    }

    private void printCardsIfNeverHit(final Player player, final boolean hasHit) {
        if (!hasHit) {
            outputView.printPlayerCards(player);
        }
    }

    private boolean askHitAndProcess(final Deck deck, final Player player) {
        final boolean wantsHit = inputView.readHitDecision(player.getName());
        hitDecision.replace(player, wantsHit);
        if (!wantsHit) {
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
        final GameResults gameResults = GameResults.create(players, dealer);
        outputView.printFinalResults(gameResults);
    }
}
