package domain.participant;

import domain.card.Card;
import domain.card.CardHand;
import java.util.List;

public abstract class Gambler {

    protected final String name;
    protected final CardHand cardHand;

    protected Gambler(String name, CardHand cardHand) {
        this.name = name;
        this.cardHand = cardHand;
    }

    public abstract boolean canTakeMoreCard();

    public final void takeCards(Card... cards) {
        cardHand.takeCards(cards);
    }

    public final boolean isBlackJack() {
        return cardHand.isBlackJack();
    }

    public final boolean isBust() {
        return cardHand.isBust();
    }

    public final int calculateScore() {
        return cardHand.calculateScore();
    }

    public final String getName() {
        return name;
    }

    public final List<Card> getCards() {
        return cardHand.getCards();
    }
}

