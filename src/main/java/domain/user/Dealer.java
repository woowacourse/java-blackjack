package domain.user;

import domain.card.Card;

public class Dealer extends User {
    Dealer() {
        super(new Name("딜러"));
    }

    public Card getVisibleCard() {
        return this.userDeck.getFirstCard();
    }
}
