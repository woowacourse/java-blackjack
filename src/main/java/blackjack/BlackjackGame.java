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

    private final InputView inputView;
    private final OutputView outputView;

    private Deck deck;
    private Dealer dealer;
    private Players players;

    public BlackjackGame(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        setupGame();
        dealInitialCards(deck, players, dealer);
        outputView.printInitialDeal(players, dealer);
        processPlayersTurn(deck, players);
        processDealerTurn(deck, dealer);
        printResults(players, dealer);
    }


    private void setupGame() {
        deck = new Deck();
        dealer = new Dealer();
        players = createPlayers();
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
            dealOneRound(deck, players, dealer);
        }
    }

    private void dealOneRound(final Deck deck, final Players players, final Dealer dealer) {
        players.getPlayers().forEach(player -> player.receiveCard(deck.draw()));
        dealer.receiveCard(deck.draw());
    }

    private void processPlayersTurn(final Deck deck, final Players players) {
        players.getPlayers().forEach(player -> processPlayerTurn(deck, player));
    }

    private void processPlayerTurn(final Deck deck, final Player player) {
        boolean hasHit = false;
        while (player.canReceiveCard()) {
            final boolean hit = askHitAndProcess(deck, player);
            hasHit = hasHit || hit;
        }
        printCardsIfNeverHit(player, hasHit);
    }

    private void printCardsIfNeverHit(final Player player, final boolean hasHit) {
        if (!hasHit) {
            outputView.printPlayerCards(player);
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
