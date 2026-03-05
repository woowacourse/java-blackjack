package domain;

import java.util.ArrayList;

public abstract class Participant {
    private final Name name;
    protected final Cards cards;

    public Participant(Name name) {
        this.name = name;
        cards = new Cards(new ArrayList<>());
    }

    // TODO: 어머 결합도가 생겨버렸네? Cards를 의존합니다.
    public void receiveCard(Card card) {
        cards.addCard(card);
    }

    public abstract boolean canReceiveCard();

    public String getName() {
        return name.toString();
    }

    public int getScore() {
        return cards.calculateScore();
    }
}
