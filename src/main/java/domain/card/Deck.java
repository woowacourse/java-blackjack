package domain.card;

import domain.ExceptionMessage;
import domain.Rank;
import domain.Suit;

import java.util.*;
import java.util.stream.IntStream;

public class Deck {
    private final Queue<Card> cards;

    public Deck() {
        List<Card> cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
        Collections.shuffle(cards);
        this.cards = new LinkedList<>(cards);
    }

    public List<Card> drawWithAmount(int amount) {
        validateAmount(amount);
        return IntStream.range(0, amount)
                .mapToObj(i -> draw())
                .toList();
    }

    private void validateAmount(int amount) {
        if (amount <= 0 || amount > cards.size()) {
            throw new IllegalArgumentException("뽑을 수 있는 카드의 범위를 벗어났습니다!");
        }
    }

    public Card draw() {
        validateIsEmpty();
        return cards.poll();
    }

    private void validateIsEmpty() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessage.EMPTY_CARDS.getMessage());
        }
    }

}
