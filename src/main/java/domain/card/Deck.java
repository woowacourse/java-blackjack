package domain.card;

import domain.Rank;
import domain.Suit;

import java.util.*;
import java.util.stream.IntStream;

public class Deck {
    private static final String EMPTY_CARDS = "[ERROR] 뽑을 수 있는 카드가 존재하지 않습니다.";
    private static final String INVALID_DRAW_AMOUNT = "[ERROR] 카드는 한 장 이상 뽑아야 합니다.";

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
        validateAmountIsPositive(amount);
        return IntStream.range(0, amount)
                .mapToObj(i -> draw())
                .toList();
    }

    private void validateAmountIsPositive(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(INVALID_DRAW_AMOUNT);
        }
    }

    public Card draw() {
        validateIsEmpty();
        return cards.poll();
    }

    private void validateIsEmpty() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_CARDS);
        }
    }

}
