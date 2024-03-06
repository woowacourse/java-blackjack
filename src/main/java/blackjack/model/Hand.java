package blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private static final int BLACK_JACK_CONDITION = 21;
    private final List<Card> cards;

    public Hand(final NumberGenerator numberGenerator) {
        this.cards = new ArrayList<>(deal(numberGenerator));
    }

    private List<Card> deal(final NumberGenerator numberGenerator) {
        return List.of(new Card(numberGenerator), new Card(numberGenerator));
    }

    public int calculateCardsTotal() {
        return cards.stream()
                .map(Card::getDenomination)
                .mapToInt(Denomination::getScore)
                .sum();
    }

    public boolean isBlackJack() {
        return calculateCardsTotal() == BLACK_JACK_CONDITION;
    }

    public List<Card> getCards() {
        return cards;
    }
}
