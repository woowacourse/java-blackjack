package domain.user;

import domain.CardHand;
import domain.TrumpCard;
import java.util.List;

public abstract class User {
    protected final CardHand cardHand;

    protected User() {
        this.cardHand = new CardHand();
    }

    public abstract boolean isImpossibleDraw();

    public abstract List<TrumpCard> openInitialCard();

    public abstract String getName();

    public void drawCard(TrumpCard card) {
        cardHand.addTrumpCard(card);
    }

    public int getSize() {
        return cardHand.cardsSize();
    }

    public CardHand getCardHand() {
        return this.cardHand;
    }

    public boolean isBust() {
        return this.cardHand.checkOverScore();
    }
}
