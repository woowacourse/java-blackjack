package blackjack.domain.card;

import java.util.Stack;

public class Deck {

    static final String ERROR_DECK_IS_EMPTY = "더이상 카드를 뽑을 수 없습니다.";
    
    private final Stack<Card> deck;

    public Deck(final DeckFactory deckFactory) {
        this.deck = deckFactory.generate();
    }

    public Card pop() {
        if (deck.isEmpty()) {
            throw new IllegalStateException(ERROR_DECK_IS_EMPTY);
        }
        return deck.pop();
    }

    Stack<Card> getDeck() {
        return deck;
    }
}
