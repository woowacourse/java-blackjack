package blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {
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

    public List<Card> getCards() {
        return cards;
    }
}
