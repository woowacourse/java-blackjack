package domain.gamer;

import domain.card.Card;
import exception.CardReceiveException;
import java.util.List;

public abstract class Gamer {
    private Name name;
    private Hand hand;

    public Gamer(final Name name) {
        this.name = name;
        this.hand = new Hand();
    }

    abstract int getStayCondition();

    public boolean canHit() {
        return calculateTotalScore() < getStayCondition();
    }

    public void receiveInitialCard(final Card card) {
        hand.add(card);
    }

    public void hit(final Card card) {
        hand.add(card);
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackJack() {
        return hand.isBlackJack();
    }

    public int calculateTotalScore() {
        return hand.sum();
    }

    public List<Card> getCardsInHand() {
        return hand.getCards();
    }

    public String getName() {
        return name.getValue();
    }
}
