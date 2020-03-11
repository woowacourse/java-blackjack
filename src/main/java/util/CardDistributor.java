package util;

import domain.card.CardDeck;
import domain.user.User;

public class CardDistributor {
    private CardDistributor() {
    }

    public static void giveOneCard(CardDeck cardDeck, User user) {
        user.addCard(cardDeck.drawOne());
    }
}
