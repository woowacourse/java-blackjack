package domain.user;

import domain.CardHand;
import domain.Card;
import java.util.List;

public abstract class User {
    protected final CardHand cardHand;

    protected User() {
        this.cardHand = new CardHand();
    }

    public abstract boolean isDrawable();

    public abstract List<Card> openInitialCard();

    public abstract String getName();

    public void drawCard(Card card) {
        cardHand.addTrumpCard(card);
    }

    public int getSize() {
        return cardHand.cardsSize();
    }

    public int calculateScore() {
        return this.cardHand.calculateScore();
    }

    public boolean isBlackjack() {
        return this.cardHand.isBlackjack();
    }

    public void addTrumpCard(Card card) {
        this.cardHand.addTrumpCard(card);
    }

    public boolean isBust() {
        return this.cardHand.checkOverScore();
    }
}
