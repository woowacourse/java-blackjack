package domain;

import java.util.List;

public class Dealer {
    private static final String NAME = "딜러";
    private final Name name;
    private final Cards cards;

    public Dealer() {
        this.name = new Name(NAME);
        cards = new Cards();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Name getName() {
        return name;
    }

    public Card getCard(int index) {
        return cards.getCard(index);
    }

    public List<String> getCards() {
        return cards.getCards();
    }

}
