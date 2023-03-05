package domain.card;

import java.util.ArrayList;
import java.util.List;

public final class Cards {

    private static final int SOFT_ADD = 10;
    private static final int SOFT_CONDITION = 21 - SOFT_ADD;
    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void takeCard(final Card card) {
        cards.add(card);
    }

    private int sumScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private static boolean isHard(final int sum) {
        return sum <= SOFT_CONDITION;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(this::isAce);
    }

    private boolean isAce(Card card) {
        return card.getRank() == Rank.ACE;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public int getScore() {
        final int score = sumScore();

        if (hasAce() && isHard(score)) {
            return score + SOFT_ADD;
        }
        return score;
    }
}
