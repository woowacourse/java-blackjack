package blackjack.domain.card;

import java.util.Collections;
import java.util.List;

import blackjack.domain.card.generator.DeckGenerator;

public class Deck {

    private final List<Card> cards;

    private Deck(final List<Card> cards) {
        validateCardNotDuplicated(cards);
        this.cards = cards;
    }

    private static void validateCardNotDuplicated(final List<Card> cards) {
        if (isCardDuplicated(cards)) {
            throw new IllegalArgumentException("중복된 카드는 존재할 수 없습니다.");
        }
    }

    private static boolean isCardDuplicated(final List<Card> cards) {
        return cards.stream()
                .anyMatch(card -> Collections.frequency(cards, card) > 1);
    }

    public static Deck generate(final DeckGenerator strategy) {
        return new Deck(strategy.generate());
    }

    public Card drawCard() {
        validateCardDrawable();
        return cards.remove(0);
    }

    private void validateCardDrawable() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("더 이상 뽑을 수 있는 카드가 없습니다.");
        }
    }

}
