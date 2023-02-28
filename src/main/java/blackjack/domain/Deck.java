package blackjack.domain;

import java.util.List;

public class Deck {

    private static final int CARDS_SIZE = 52;

    private final List<Card> cards;

    public Deck(List<Card> cards) {
        validate(cards);
        this.cards = cards;
    }

    private void validate(List<Card> cards) {
        validateSize(cards);
    }

    private void validateSize(List<Card> cards) {
        if (cards.size() != CARDS_SIZE) {
            throw new IllegalArgumentException("카드들의 갯수는 52장이어야 합니다.");
        }
    }
}
