package domain.participant;

import domain.card.Card;
import java.util.List;

public class Participant {

    private static final int BUST_BOUNDARY_EXCLUSIVE = 21;
    private static final int BONUS_SCORE = 10;
    private final Name name;
    private final List<Card> cards;

    public Participant(final Name name, final List<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    public void receiveCard(Card card) {
        this.cards.add(card);
    }

    public int calculateScore() {
        int defaultScore = calculateNonAceSum();
        boolean hasAce = hasAce();
        boolean canAddBonusScore = defaultScore + BONUS_SCORE <= BUST_BOUNDARY_EXCLUSIVE;

        if (hasAce && canAddBonusScore) {
            defaultScore += BONUS_SCORE;
        }
        return defaultScore;
    }

    private int calculateNonAceSum() {
        return cards.stream()
                .mapToInt(Card::getDefaultScore)
                .sum();
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    public boolean isBust() {
        return calculateScore() > BUST_BOUNDARY_EXCLUSIVE;
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> getCards() {
        return cards;
    }
}
