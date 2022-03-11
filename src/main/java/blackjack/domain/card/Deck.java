package blackjack.domain.card;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import blackjack.domain.card.strategy.DeckGenerator;

public class Deck {

    private final List<Card> cards;

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

    public static Deck generate(final DeckGenerator strategy) {
        return new Deck(strategy.generate());
    }

    public Card drawCard() {
        validateDrawCard();
        return cards.remove(0);
    }

    private void validateDrawCard() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("더 이상 뽑을 수 있는 카드가 없습니다.");
        }
    }

}
