package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    void add(Card card) {
        cards.add(card);
    }

    boolean hasACE() {
        return cards.stream()
                .anyMatch(Card::isACE);
    }

    int getTotalScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    List<Card> getAllCards() {
        return cards;
    }
}
