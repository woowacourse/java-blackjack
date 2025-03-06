package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Deck {
    private final Stack<Card> deck = new Stack<>();

    public Deck(final List<Card> cards) {
        validateDuplicate(cards);
        List<Card> copiedCards = new ArrayList<>(cards);
        Collections.shuffle(copiedCards);
        copiedCards.forEach(deck::push);
    }

    public Card draw() {
        validateEmpty();
        return deck.pop();
    }

    private void validateDuplicate(List<Card> cards) {
        Set<Card> cardSet = new HashSet<>(cards);
        if (cardSet.size() != cards.size()) {
            throw new IllegalArgumentException("중복된 카드로 덱을 생성할 수 없습니다.");
        }
    }

    private void validateEmpty() {
        if (deck.isEmpty()) {
            throw new IllegalStateException("카드가 없습니다.");
        }
    }
}
