package blackjack.domain.deck;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Deck {

    private static final int INITIAL_CARD_COUNT = 2;

    private final Deque<Card> cards;

    public Deck(DeckCreateStrategy deckCreateStrategy) {
        this.cards = deckCreateStrategy.createDeck();
    }

    public List<Card> drawInitialCards() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            cards.add(draw());
        }
        return cards;
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("덱에 카드가 더이상 없습니다.");
        }
        return cards.pop();
    }
}
