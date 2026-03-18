package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Participant {
    private final Name name;
    private final Cards cards;

    public Participant(Name name) {
        this.name = name;
        cards = new Cards(new ArrayList<>());
    }

    public void receiveCard(Card card) {
        cards.addCard(card);
    }

    public boolean canReceiveCard() {
        return cards.canReceiveCard(getHitThreshold());
    }

    public boolean isBlackJack() {
        return cards.isBlackJack();
    }

    public boolean isBust() {
        return cards.isBust();
    }

    protected abstract int getHitThreshold();

    public String getName() {
        return name.getName();
    }

    public int getScore() {
        return cards.calculateOptimalScore();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Participant participant = (Participant) o;
        return Objects.equals(getName(), participant.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }
}
