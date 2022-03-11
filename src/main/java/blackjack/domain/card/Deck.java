package blackjack.domain.card;

import static java.util.Arrays.*;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {

    private final Deque<Card> cards;

    public Deck() {
        cards = shuffleCards(createCards());
    }

    private Deque<Card> shuffleCards(List<Card> cards) {
        Collections.shuffle(cards);
        return new ArrayDeque<>(cards);
    }

    private List<Card> createCards() {
        return stream(Suit.values())
                .flatMap(suit -> stream(Denomination.values())
                        .map(denomination -> new Card(suit, denomination)))
                .collect(Collectors.toList());
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
