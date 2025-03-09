package domain.user;

import domain.CardDeck;
import domain.TrumpCardManager;
import domain.TrumpCard;
import java.util.List;

public abstract class User {
    protected final String name;
    protected final CardDeck cardDeck;

    public User(String name) {
        this.name = name;
        this.cardDeck = new CardDeck();
    }

    public abstract boolean isImpossibleDraw();

    public void drawCard() {
        TrumpCard trumpCard = TrumpCardManager.drawCard();
        cardDeck.addTrumpCard(trumpCard);
    }

    public boolean has(String name) {
        return getName().equals(name);
    }

    public String getName() {
        return this.name;
    }

    public int getSize() {
        return cardDeck.cardsSize();
    }

    public abstract List<TrumpCard> openCard();

    public CardDeck getCardDeck() {
        return this.cardDeck;
    }

    public boolean isBurst() {
        return this.cardDeck.checkOverScore();
    }
}
