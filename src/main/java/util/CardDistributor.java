package util;

import domain.card.CardDeck;
import domain.user.Player;

public class CardDistributor {
    private CardDistributor() {
    }

    public static void giveOneCard(final CardDeck cardDeck, final Player player) {
        player.addCard(cardDeck.drawOne());
    }
}
