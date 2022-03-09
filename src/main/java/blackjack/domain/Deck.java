package blackjack.domain;

import blackjack.domain.strategy.CardStrategy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Deck {

    private List<Card> cards;

    private Deck(final List<Card> cards) {
        validateDuplicateCard(cards);
        this.cards = cards;
    }

    private static void validateDuplicateCard(final List<Card> cards) {
        final Set<Card> validCards = new HashSet<>(cards);
        if (validCards.size() != cards.size()) {
            throw new IllegalArgumentException("중복된 카드는 존재할 수 없습니다.");
        }
    }

    public static Deck generate(final CardStrategy strategy) {
        return new Deck(strategy.generate());
    }

    public Card drawCard() {
        return cards.remove(0);
    }
}
