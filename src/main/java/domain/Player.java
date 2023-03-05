package domain;

import java.util.List;

public abstract class Player {

    private final String name;
    private final Cards cards;

    public Player(String name, List<Card> cards) {
        this.name = name;
        this.cards = new Cards(cards);
    }

    void addCard(Card card) {
        cards.add(card);
    }

    public int getScore() {
        return cards.getScore();
    }

    Result competeWith(Player other) {
        return cards.competeWith(other.cards);
    }

    public abstract boolean canHit();

    boolean isBusted() {
        return getScore() > 21;
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public String getName() {
        return name;
    }
}