package blackjack.domain;

import java.util.List;

public class Cards {

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        this.cards = cards;
    }

    public int calculateScore() {
        return cards.stream()
                .map(Card::getNumber)
                .mapToInt(CardNumber::getDefaultValue)
                .sum();
    }
}
