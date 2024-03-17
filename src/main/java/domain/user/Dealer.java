package domain.user;

import domain.deck.UserDeck;

public class Dealer extends User {
    private static final int DEALER_CARD_CONDITION = 16;

    Dealer() {
        super(new UserDeck(), new Name("딜러"));
    }

    public boolean isDealerCardAddable() {
        return userDeck.sumCard() <= DEALER_CARD_CONDITION;
    }

}
