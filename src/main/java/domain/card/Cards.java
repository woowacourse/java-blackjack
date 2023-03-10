package domain.card;

import domain.participant.Score;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private static final Score BUST_BOUNDARY_EXCLUSIVE = new Score(21);
    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void receiveInitialCards(final List<Card> initialCards) {
        cards.addAll(initialCards);
    }

    public void receiveCard(Card card) {
        this.cards.add(card);
    }

    public boolean isBust() {
        return calculateScore().isGreaterThan(BUST_BOUNDARY_EXCLUSIVE);
    }

    public Score calculateScore() {
        boolean hasAce = hasAce();
        Score defaultScore = new Score(calculateDefaultScore());
        boolean canAddBonusScore = defaultScore.canAddBonusScore();

        if (hasAce && canAddBonusScore) {
            defaultScore = defaultScore.addBonusScore();
        }
        return defaultScore;
    }

    private int calculateDefaultScore() {
        return cards.stream()
                .mapToInt(Card::getDefaultScore)
                .sum();
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
