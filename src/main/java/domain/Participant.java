package domain;

import java.util.List;

public abstract class Participant {
    private final Name name;
    private final Cards cards;

    protected Participant(String name) {
        this.name = new Name(name);
        this.cards = new Cards();
    }

    public abstract List<Card> getInitialVisibleCards();

    public abstract boolean isDrawable();

    public boolean isLessThanMaxScore() {
        return cards.isLessThanMaxScore();
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public void addCard(Card newCard) {
        cards.addCard(newCard);
    }

    public int getCardsSum() {
        return cards.calculateCardScoreSum();
    }

    public String getName() {
        return name.name();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }
}
