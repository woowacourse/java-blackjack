package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private final List<Card> cards = new ArrayList<>();

    // TODO: 메서드 분리, 상수 분리 필요
    public int getSum() {
        int minimumSum = cards.stream()
                .mapToInt(Card::getNumber)
                .sum();

        final int aceCount = getAceCount();
        for (int i = 0; i < aceCount; i++) {
            if (minimumSum + 10 <= 21) {
                minimumSum += 10;
            }
        }

        return minimumSum;
    }

    private int getAceCount() {
        return (int) cards.stream()
                .filter(Card::isAceCard)
                .count();
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public List<Card> getAllCards() {
        return Collections.unmodifiableList(cards);
    }
}
