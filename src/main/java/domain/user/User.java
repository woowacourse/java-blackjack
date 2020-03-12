package domain.user;

import domain.card.CardDeck;
import domain.card.Cards;

public abstract class User {

    private static final int DEFAULT_DRAW_COUNT = 1;
    protected Cards cards = new Cards();

    public void drawCard(CardDeck cardDeck) {
        drawCard(cardDeck, DEFAULT_DRAW_COUNT);
    }

    public void drawCard(CardDeck cardDeck, int cardCount) {
        for (int i = 0; i < cardCount; i++) {
            cards.add(cardDeck.draw());
        }
    }

    public abstract boolean canDrawCard();

    public Cards getCards() {
        return cards;
    }
}
