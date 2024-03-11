package domain.user;

import domain.deck.DealerDeck;

public class Dealer extends User {
    Dealer() {
        super(new DealerDeck());
    }

    public boolean isAddable() {
        return userDeck.isAddable();
    }

    @Override
    public boolean isPlayer() {
        return false;
    }

    @Override
    public String getNameValue() {
        return "";
    }
}
