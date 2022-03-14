package blackjack.domain.card;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck implements DrawStrategy{

    private static final String EMPTY_CARD_ERROR = "남은 카드가 존재하지 않습니다.";

    private final Stack<Card> deck = new Stack<>();

    public Deck(List<Card> deck) {
        this.deck.addAll(deck);
        Collections.shuffle(this.deck);
    }

    @Override
    public Card draw() {
        validateEmptyDeck();
        return deck.pop();
    }

    private void validateEmptyDeck() {
        if (deck.isEmpty()) {
            throw new IllegalStateException(EMPTY_CARD_ERROR);
        }
    }

    public int getSize() {
        return deck.size();
    }
}
