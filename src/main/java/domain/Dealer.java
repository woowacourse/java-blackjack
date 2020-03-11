package domain;

public class Dealer extends Player {
    private static final int STANDARD = 16;

    public Dealer(Card... cards) {
        super(cards);
    }

    public void insertAdditionalCard(Cards cards) {
        if (CardCalculator.isUnderSixteen(this)) {
            insertCard(cards);
        }
    }

    @Override
    public void insertCard(Cards cards) {
        if (sumCardNumber() <= STANDARD) {
            this.cards.add(cards.pop());
        }
    }
}
