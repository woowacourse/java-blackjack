package blackjack;

import blackjack.domain.deck.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.GameResults;
import java.util.stream.IntStream;

public class BlackjackBoard {

    private static final int INITIAL_DEAL_COUNT = 2;

    private final Deck deck;
    private final Dealer dealer;
    private final Players players;

    public BlackjackBoard(final Deck deck, final Dealer dealer, final Players players) {
        this.deck = deck;
        this.dealer = dealer;
        this.players = players;
    }

    public void dealInitialCards() {
        IntStream.range(0, INITIAL_DEAL_COUNT)
                .forEach(i -> {
                    players.getPlayers().forEach(player -> player.receiveCard(deck.draw()));
                    dealer.receiveCard(deck.draw());
                });
    }

    public void hitPlayer(final Player player) {
        player.receiveCard(deck.draw());
    }

    public void hitDealer() {
        dealer.receiveCard(deck.draw());
    }

    public void stayPlayer(final Player player) {
        player.stay();
    }

    public boolean dealerCanReceiveCard() {
        return dealer.canReceiveCard();
    }

    public GameResults calculateResults() {
        return GameResults.calculate(players, dealer);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
