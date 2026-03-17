package domain.player;

import domain.card.Card;
import java.util.List;

public abstract class Participant {

    private final Name name;

    protected Participant(Name name) {
        this.name = name;
    }

    public String name() {
        return name.getName();
    }

    public List<Card> cards() {
        return getHand().getCards();
    }

    public int totalScore() {
        return getHand().calculateTotalScore();
    }

    public boolean isBust() {
        return getHand().isBust();
    }

    public boolean isBlackjack() {
        return getHand().isBlackjack();
    }

    protected abstract Hand getHand();
}