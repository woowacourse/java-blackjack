package domain;

public class Dealer extends User {
    public Dealer() {
        cards = new DealerCards();
    }

    @Override
    public boolean isAbleDrawCards() {
        return !((DealerCards) cards).isOverSixteen();
    }
}
