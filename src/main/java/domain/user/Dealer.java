package domain.user;

import domain.card.Card;
import domain.deck.DealerDeck;

public class Dealer extends User {
    Dealer() {
        super(new DealerDeck(), new Name("딜러"));
    }

    public Card getVisibleCard() {
        DealerDeck dealerDeck = (DealerDeck) userDeck;
        return dealerDeck.getVisibleCard();
    }

    @Override
    boolean isPlayer() {
        return false;
    }

    public boolean isAddCondition() {
        DealerDeck dealerDeck = (DealerDeck) userDeck;
        return dealerDeck.isAddCondition();
    }
}
