package domain;

import java.util.List;

public abstract class Participant {
    protected final Cards cards = new Cards();

    public void receive(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        return cards.calculateScore();
    }

    public boolean isBusted() {
        return calculateScore() > BlackjackRule.BUST_LIMIT.getValue();
    }

    public int cardSize() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }
}
