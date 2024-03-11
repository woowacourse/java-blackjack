package blackjack.domain.cardgame;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;

import java.util.List;

public class CardGame {
    private static final int INITIAL_CARD_NUMBER = 2;

    private final CardDeck cardDeck;

    public CardGame() {
        this.cardDeck = CardDeck.create();
    }

    public void initializeHand(final Dealer dealer, final List<Player> players) {
        giveDealerInitialCards(dealer);
        giveEachPlayerInitialCards(players);
    }

    private void giveDealerInitialCards(final Dealer dealer) {
        giveInitialCards(dealer);
    }

    private void giveEachPlayerInitialCards(final List<Player> players) {
        for (final Player player : players) {
            giveInitialCards(player);
        }
    }

    private void giveInitialCards(Player player) {
        for (int i = 0; i < INITIAL_CARD_NUMBER; i++) {
            giveCard(player);
        }
    }

    public void giveCard(final Player player) {
        player.addCard(cardDeck.draw());
    }
}
