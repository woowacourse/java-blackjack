package helper;

import balckjack.domain.Card;
import balckjack.strategy.CardPicker;

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