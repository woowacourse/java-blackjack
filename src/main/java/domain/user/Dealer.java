package domain.user;

import domain.card.Card;
import domain.deck.UserDeck;

import java.util.List;

public class Dealer extends User {
    private static final int FIRST_INDEX = 0;
    private static final int DEALER_CARD_CONDITION = 16;

    Dealer() {
        super(new UserDeck(), new Name("딜러"));
    }

    public Card getVisibleCard() {
        List<Card> dealerDeck = userDeck.getCards();
        return dealerDeck.get(FIRST_INDEX);
    }

    public boolean isDealerCardAddable() {
        return userDeck.sumCard() <= DEALER_CARD_CONDITION;
    }

}
