package domain;

import java.util.ArrayList;
import java.util.List;

public final class Cards {

    private static final int ACE_ELEVEN = 10;
    private static final int ACE_CONDITION = 21 - ACE_ELEVEN;
    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void takeCard(final Card card) {
        cards.add(card);
    }


    public int getScore() {
        final int score = sumScore();

        if (hasAce() && isUnderTwentyOne(score)) {
            return score + ACE_ELEVEN;
        }
        return score;
    }

    private int sumScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public List<Card> displayCards() {
        return List.copyOf(cards);
    }

    private static boolean isUnderTwentyOne(final int sum) {
        return sum <= ACE_CONDITION;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(this::isAce);
    }

    private boolean isAce(Card card) {
        return card.getNumber() == Number.ACE;
    }
}
