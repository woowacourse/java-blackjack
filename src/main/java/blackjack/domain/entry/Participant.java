package blackjack.domain.entry;

import blackjack.domain.card.Card;
import blackjack.domain.card.HoldCards;

import java.util.List;

public abstract class Participant {
    private final HoldCards holdCards;

    protected Participant(HoldCards holdCards) {
        this.holdCards = holdCards;
    }

    public abstract String getName();

    public abstract List<Card> openCard();

    public int countCards() {
        return holdCards.countBestNumber();
    }

    public void putCard(Card card) {
        holdCards.addCard(card);
    }

    public HoldCards getHoldCards() {
        return holdCards;
    }
}
