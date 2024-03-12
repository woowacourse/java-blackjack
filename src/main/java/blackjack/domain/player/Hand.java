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

        final int sumWithAdditionalScore = minimumSum + 10;
        if (hasAceCard() && sumWithAdditionalScore <= 21) {
            return sumWithAdditionalScore;
        }
        return minimumSum;
    }

    private boolean hasAceCard() {
        return cards.stream()
                .anyMatch(Card::isAceCard);
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public List<Card> getAllCards() {
        return Collections.unmodifiableList(cards);
    }
}
