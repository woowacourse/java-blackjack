package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;

public class BlackjackGame {

    private static final int NUMBER_OF_STARTING_CARDS = 2;

    private final Deck deck;

    public BlackjackGame(final Deck deck) {
        this.deck = deck;
    }

    public void initStartingCards(final Dealer dealer, final Players players) {
        drawStartingCards(dealer);
        for (Player player : players.getPlayers()) {
            drawStartingCards(player);
        }
    }

    private void drawStartingCards(final Gamer gamer) {
        for (int i = 0; i < NUMBER_OF_STARTING_CARDS; i++) {
            gamer.drawCard(deck.draw());
        }
    }
}
