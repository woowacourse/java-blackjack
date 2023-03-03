package helper;

import blackjack.domain.Card;
import blackjack.strategy.CardPicker;

public class StubCardPicker implements CardPicker {

    private final Card expected;

    public StubCardPicker(Card card) {
        this.expected = card;
    }

    @Override
    public Card pick() {
        return expected;
    }
}