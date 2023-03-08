package domain;

import java.util.List;

public class Player {

    private final Name name;
    private final Cards cards;

    public Player(final Name name, final Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public boolean selectToPickOtherCard(final CardBox cardBox, final int cardBoxIndex) {
        return pickOtherCard(cardBox, cardBoxIndex);
    }

    protected boolean pickOtherCard(final CardBox cardBox, final int cardBoxIndex) {
        return cards.addCard(cardBox.get(cardBoxIndex));
    }

    public int sumOfPlayerCards() {
        return cards.sumOfCards();
    }

    public String getName() {
        return name.getName();
    }

    public Cards getCards() {
        return cards;
    }

    public List<String> copyCards() {
        return cards.copyCards();
    }
}
