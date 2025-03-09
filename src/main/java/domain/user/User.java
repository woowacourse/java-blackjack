package domain.user;

import domain.CardHand;
import domain.CardDeck;
import domain.TrumpCard;
import java.util.List;

public abstract class User {
    protected final String name;
    protected final CardHand cardDeck;

    protected User(String name) {
        this.name = name;
        this.cardDeck = new CardHand();
    }

    public abstract boolean isImpossibleDraw();

    public abstract List<TrumpCard> openCard();

    public void drawCard() {
        TrumpCard trumpCard = CardDeck.drawCard();
        cardDeck.addTrumpCard(trumpCard);
    }

    public boolean hasName(String name) {
        return this.name.equals(name);
    }

    public String getName() {
        return this.name;
    }

    public int getSize() {
        return cardDeck.cardsSize();
    }

    public CardHand getCardDeck() {
        return this.cardDeck;
    }

    public boolean isBurst() {
        return this.cardDeck.checkOverScore();
    }
}
