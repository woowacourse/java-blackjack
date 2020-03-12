package domain.user;

import domain.card.CardDeck;
import domain.card.Cards;

public abstract class User {

    protected Cards cards;

    public void drawCard(CardDeck cardDeck) {
        drawCard(cardDeck, 1);
    }

    public void drawCard(CardDeck cardDeck, int cardCount) {
        for (int i = 0; i < cardCount; i++) {
            cards.add(cardDeck.draw());
        }
    }

    public abstract boolean isAbleDrawCards();

    public Cards getCards() {
        return cards;
    }
}
