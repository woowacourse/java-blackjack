package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private List<Card> cards;

    public Hand() {
        this(Collections.emptyList());
    }

    public Hand(Card... cards) {
        this(List.of(cards));
    }

    public Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        boolean hasAce = cards.stream()
                .anyMatch(Card::isAce);
        int score = cards.stream()
                .mapToInt(Card::getScore)
                .sum();
        if (hasAce && score + 10 <= 21) {
            score += 10;
        }
        return score;
    }

    public List<Card> getCards() {
        return cards;
    }
}
