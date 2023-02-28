package domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void takeCard(Card card) {
        cards.add(card);
    }


    public int sumScore() {
        final int score = cards.stream()
                .mapToInt(Card::getScore)
                .sum();

        if (hasAce() && isUnderTwentyOne(score)) {
            return score + 10;
        }
        return score;
    }

    private static boolean isUnderTwentyOne(final int sum) {
        return sum <= 11;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(this::isAce);
    }

    private boolean isAce(Card card) {
        return card.getNumber() == Number.ACE;
    }
}
