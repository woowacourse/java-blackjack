package domain;

import java.util.ArrayList;
import java.util.List;

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
}
