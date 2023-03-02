package domain;

import java.util.List;

public class Player {

    private final Name name;
    private final Cards cards;

    public Player(final Name name, final Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public boolean selectToPickOtherCard(final CardBox cardBox, final CardNumberGenerator generator) {
        return pickOtherCard(cardBox, generator);
    }

    protected boolean pickOtherCard(final CardBox cardBox, final CardNumberGenerator generator) {
        return cards.addCard(cardBox.get(generator.generateIndex()));
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
}
