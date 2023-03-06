package helper;

import blackjack.domain.card.Card;
import blackjack.strategy.CardPicker;

import java.util.List;

public class StubCardPicker implements CardPicker {

    private final Card expected;

    public StubCardPicker(Card card) {
        this.expected = card;
    }

    @Override
    public Card pick(List<Card> cards) {
        return expected;
    }
}