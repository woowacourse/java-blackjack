package domain.user;

import domain.card.Card;
import domain.deck.DealerDeck;

public class Dealer extends User {
    Dealer() {
        super(new DealerDeck(), new Name("딜러"));
    }

    public Card getVisibleCard() {
        DealerDeck deck = (DealerDeck) userDeck;
        return deck.getFirstCard();
    }

    public boolean isDealerCardAddCondition() {
        DealerDeck deck = (DealerDeck) userDeck;
        return deck.isAdd();
    }

    @Override
    public boolean isPlayer() {
        return false;
    }
}
