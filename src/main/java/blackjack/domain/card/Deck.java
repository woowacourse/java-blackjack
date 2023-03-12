package blackjack.domain.card;

import java.util.*;

public class Deck {
    private final Deque<Card> cards;

    public Deck() {
        List<Card> cards = new ArrayList<>();
        for (Suit shape : Suit.values()) {
            Arrays.stream(CardNumber.values())
                    .forEach(number -> cards.add(Card.of(shape, number)));
        }
        Collections.shuffle(cards);
        this.cards = new ArrayDeque<>(cards);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("덱이 비어 있을 때 카드를 뽑을 수 없습니다.");
        }
        return cards.pop();
    }
}
