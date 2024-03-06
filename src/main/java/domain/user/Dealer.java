package domain.user;

import domain.card.Card;

public class Dealer extends User {
    Dealer() {
        super();
    }

    public Card getVisibleCard() {
        return this.userDeck.getCards().get(0);
    }
}
