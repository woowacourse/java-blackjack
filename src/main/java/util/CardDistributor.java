package util;

import domain.card.CardDeck;
import domain.user.User;

public class CardDistributor {
    private CardDistributor() {
    }

    public static void giveOneCard(final CardDeck cardDeck, final User user) {
        user.addCard(cardDeck.drawOne());
    }
}
