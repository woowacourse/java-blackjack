package player;

import java.util.ArrayList;
import java.util.List;

import card.Card;

public class Hand {
    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public int calculateScore() {
        int score = cards.stream()
                .mapToInt(Card::getScore)
                .sum();
        boolean containAce = cards.stream()
                .anyMatch(Card::isAce);
        if (containAce && score <= 11) {
            score += 10;
        }
        return score;
    }
}
