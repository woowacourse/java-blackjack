package domain.user;

import domain.CardHand;
import domain.CardDeck;
import domain.TrumpCard;
import java.util.List;

public abstract class User {
    protected final CardHand cardDeck;

    protected User() {
        this.cardDeck = new CardHand();
    }

    public abstract boolean isImpossibleDraw();

    public abstract List<TrumpCard> openCard();

    public abstract String getName();

    public void drawCard() {
        TrumpCard trumpCard = CardDeck.drawCard();
        cardDeck.addTrumpCard(trumpCard);
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
