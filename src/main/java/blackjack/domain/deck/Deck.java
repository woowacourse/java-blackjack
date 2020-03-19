package blackjack.domain.deck;

import blackjack.domain.card.Card;

import java.util.*;

public class Deck {

    private final Stack<Card> cards = new Stack<>();

    public Deck(List<Card> cards) {
        if (Objects.isNull(cards) || cards.isEmpty()) {
            throw new IllegalArgumentException("카드 덱을 생성할 수 없습니다.");
        }
        Collections.shuffle(cards);
        this.cards.addAll(cards);
    }

    public Card pick() {
        if (cards.empty()) {
            throw new EmptyStackException();
        }
        return cards.pop();
    }
}
