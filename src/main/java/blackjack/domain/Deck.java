package blackjack.domain;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {
    private final Deque<Card> cards;

    private Deck(Deque<Card> cards) {
        this.cards = cards;
    }

    public Deck() {
        this(Arrays.stream(CardNumber.values())
                .flatMap(cardNumber -> Arrays.stream(CardSymbol.values())
                        .map(cardSymbol -> new Card(cardNumber, cardSymbol)))
                .collect(Collectors.toCollection(ArrayDeque::new)));
    }

    public Card draw() {
        validateDeckIsEmpty();
        return cards.remove();
    }

    private void validateDeckIsEmpty() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("더 이상 카드를 뽑을 수 없습니다.");
        }
    }

    public void shuffleDeck() {
        Collections.shuffle((List<?>) this.cards);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public int size() {
        return cards.size();
    }
}
