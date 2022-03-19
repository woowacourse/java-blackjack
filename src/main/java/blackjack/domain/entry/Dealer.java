package blackjack.domain.entry;

import blackjack.domain.card.Card;
import blackjack.domain.card.HoldCards;

public class Dealer {

    private static final int MORE_CARD_STANDARD = 16;
    private static final String NAME = "딜러";

    private final HoldCards holdCards;

    public Dealer(HoldCards holdCards) {
        this.holdCards = holdCards;
    }

    public boolean canHit() {
        return holdCards.countBestNumber() <= MORE_CARD_STANDARD;
    }

    public int countCards() {
        return holdCards.countBestNumber();
    }

    public HoldCards getHoldCards() {
        return holdCards;
    }

    public String getName() {
        return NAME;
    }

    public void addCard(Card card) {
        this.holdCards.addCard(card);
    }
}
