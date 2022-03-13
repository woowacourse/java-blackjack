package blackjack.domain.card;

import blackjack.domain.card.strategy.CardStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Deck {

    private final List<Card> cards;

    public Deck(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
        validateDuplicateCard(this.cards);
    }

    private void validateDuplicateCard(final List<Card> cards) {
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
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("더 이상 뽑을 수 있는 카드가 없습니다.");
        }
    }

    @Override
    public String toString() {
        return "Deck size = " + cards.size() + '{' +
                "cards=" + cards +
                '}';
    }
}
