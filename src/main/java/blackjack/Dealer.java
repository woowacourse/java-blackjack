package blackjack;

import java.util.Collections;
import java.util.List;

public class Dealer implements Participant {
    private static final int MORE_CARD_STANDARD = 16;
    private static final String NAME = "딜러";

    private HoldCards holdCards;

    public Dealer(HoldCards holdCards) {
        this.holdCards = holdCards;
    }

    @Override
    public int countCards() {
        return holdCards.countBestNumber();
    }

    @Override
    public void putCard(Card card) {
        holdCards.addCard(card);
    }

    @Override
    public List<Card> openCard() {
        return Collections.singletonList(holdCards.getFirstCard());
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public HoldCards getHoldCards() {
        return this.holdCards;
    }

    public boolean shouldHaveMoreCard() {
        return countCards() <= MORE_CARD_STANDARD;
    }

}
