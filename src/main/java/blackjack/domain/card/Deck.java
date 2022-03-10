package blackjack.domain.card;

import blackjack.domain.card.strategy.CardStrategy;

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
        validateDrawCard();
        return cards.remove(0);
    }

    private void validateDrawCard() {
        if (cards.size() == 0) {
            throw new IllegalArgumentException("더 이상 뽑을 수 있는 카드가 없습니다.");
        }
    }
}
