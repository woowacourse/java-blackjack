package domain;

import config.CardBox;

public class Player {

    private final Name name;
    private final Cards cards;

    public Player(final Name name, final Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public boolean selectToPickOtherCard(final String yesOrNo, final CardNumberGenerator generator) {
        if (yesOrNo.equals("Y")) {
            return pickOtherCard(generator);
        }
        return false;
    }

    protected boolean pickOtherCard(final CardNumberGenerator generator) {
        return cards.addCard(CardBox.cardBox.get(generator.generateIndex()));
    }

    public int sumOfPlayerCards() {
        return cards.sumOfCards();
    }
}
