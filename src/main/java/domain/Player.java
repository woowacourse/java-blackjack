package domain;

public class Player {

    private final Name name;
    private final Cards cards;

    public Player(final Name name, final Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public boolean selectToPickOtherCard(final String yesOrNo, final CardBox cardBox,
                                         final CardNumberGenerator generator) {
        if (yesOrNo.equals("Y")) {
            return pickOtherCard(cardBox, generator);
        }
        return false;
    }

    protected boolean pickOtherCard(final CardBox cardBox, final CardNumberGenerator generator) {
        return cards.addCard(cardBox.get(generator.generateIndex()));
    }

    public int sumOfPlayerCards() {
        return cards.sumOfCards();
    }
}
