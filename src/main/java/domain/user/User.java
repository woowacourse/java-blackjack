package domain.user;

import domain.card.UserCards;

public abstract class User {
    protected UserCards cards;

    public User() {
        this.cards = new UserCards();
    }
}
