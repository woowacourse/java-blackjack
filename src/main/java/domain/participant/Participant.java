package domain.participant;

import domain.Hand;
import domain.card.Card;
import java.util.List;

public abstract class Participant {
    private final String name;
    private final Hand hand = new Hand();

    protected Participant(String name) {
        this.name = name;
    }

    protected boolean hasTwoCards() {
        return hand.size() == 2;
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackJack() {
        return hand.isBlackjack();
    }

    public void receiveCard(Card card) {
        hand.add(card);
    }

    public List<String> getHandToString() {
        return hand.toStringList();
    }

    public int getScore() {
        return hand.calculateScore();
    }

    public String getName() {
        return name;
    }

    public abstract boolean canDraw();
}