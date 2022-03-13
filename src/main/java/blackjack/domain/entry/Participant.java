package blackjack.domain.entry;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.HoldCards;

import java.util.List;

public abstract class Participant {
    private final HoldCards holdCards;

    protected Participant(HoldCards holdCards) {
        this.holdCards = holdCards;
    }

    public abstract boolean canHit();

    public abstract List<Card> openCard();

    public abstract String getName();

    public int countCards() {
        return holdCards.countBestNumber();
    }

    public void addCard(Card card) {
        holdCards.addCard(card);
    }

    public HoldCards getHoldCards() {
        return holdCards;
    }
}
