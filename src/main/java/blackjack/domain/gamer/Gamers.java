package blackjack.domain.gamer;

import blackjack.domain.card.Deck;

public class Gamers {

    private final Dealer dealer;
    private final Players players;

    public Gamers(final Dealer dealer, final Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void drawInitialCards(final Deck deck) {
        dealer.drawTwoCards(deck);
        for (final Player player : players.getPlayers()) {
            player.drawTwoCards(deck);
        }
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
