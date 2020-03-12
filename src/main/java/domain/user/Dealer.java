package domain.user;

import domain.card.DealerCards;

public class Dealer extends User {

    public Dealer() {
        cards = new DealerCards();
    }

    @Override
    public boolean isAbleDrawCards() {
        return !((DealerCards) cards).isOverSixteen();
    }
}
