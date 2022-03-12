package blackjack.domain.card;

import blackjack.domain.strategy.DeckGenerateStrategy;
import java.util.Deque;

public class Deck {

    private final Deque<Card> cards;

    public Deck(DeckGenerateStrategy deckGenerateStrategy) {
        this.cards = deckGenerateStrategy.generate();
    }

    public Card drawCard() {
        validateExist();
        return cards.pop();
    }

    private void validateExist() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("덱에 남은 카드가 없습니다.");
        }
    }
}
