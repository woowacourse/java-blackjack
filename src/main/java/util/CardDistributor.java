package util;

import domain.card.CardDeck;
import domain.player.Player;

public class CardDistributor {
    private static final int DEFAULT_CARD_SIZE = 2;

    private CardDistributor() {
    }

    public static void distributeCards(final CardDeck cardDeck, final Player player) {
        int distributeCount = DEFAULT_CARD_SIZE;

        while (distributeCount-- > 0) {
            CardDistributor.giveOneCard(cardDeck, player);
        }
    }

    public static void giveOneCard(final CardDeck cardDeck, final Player player) {
        player.addCard(cardDeck.drawOne());
    }
}
