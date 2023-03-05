package domain;

import java.util.List;

public class Player {

    private final Name name;
    private final Cards cards;

    public Player(final Name name, final Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public boolean selectToPickOtherCard(final CardBox cardBox) {
        return cards.addCard(cardBox.get());
    }

    public int sumOfPlayerCards() {
        return cards.sumOfCards();
    }

    public String getName() {
        return name.getName();
    }

    public List<String> getCards() {
        return cards.getCards();
    }

    public boolean isNotBurst() {
        return cards.isNotBurst();
    }
}
