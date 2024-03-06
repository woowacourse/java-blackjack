package domain;

import java.util.List;
import view.OutputView;

public class Blackjack {

    private final Players players;
    private final Player dealer;
    private final Deck deck;

    public Blackjack(final Players players, final Player dealer, final Deck deck) {
        this.players = players;
        this.dealer = dealer;
        this.deck = deck;
    }

    public void dealCardsToPlayer(final Player player) {
        player.addCard(deck.poll());
    }

    public void initializePlayers() {

        players.getPlayers().forEach(this::initializePlayer);
    }

    public void initializeDealer() {
        dealer.addCard(deck.poll());
        dealer.addCard(deck.poll());
    }

    private void initializePlayer(final Player player) {
        player.addCard(deck.poll());
        player.addCard(deck.poll());
    }

    public Players getPlayers() {
        return players;
    }

    public Player getDealer() {
        return dealer;
    }

    public Players getAllPlayers() {
        players.getPlayers().add(dealer);
        return players;
    }
}
