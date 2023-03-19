package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private static final int BLACKJACK = 21;
    private static final int ACE_OFFSET = 10;

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getScore() {
        int score = calculateScore();

        if (hasAce() && changeAnAceTo11(score) <= BLACKJACK) {
            return changeAnAceTo11(score);
        }

        return score;
    }

    private int calculateScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    private int changeAnAceTo11(int score) {
        return score + ACE_OFFSET;
    }

    public List<Card> toList() {
        return Collections.unmodifiableList(cards);
    }
}
