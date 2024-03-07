package blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private static final int BLACK_JACK_CONDITION = 21;

    private final List<Card> cards;

    Hand(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Hand(final CardGenerator cardGenerator) {
        this.cards = new ArrayList<>(deal(cardGenerator));
    }

    private List<Card> deal(final CardGenerator cardGenerator) {
        return List.of(cardGenerator.pick(), cardGenerator.pick());
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

    public void addCard(final CardGenerator cardGenerator) {
        cards.add(cardGenerator.pick());
    }

    public List<Card> getCards() {
        return cards;
    }
}
