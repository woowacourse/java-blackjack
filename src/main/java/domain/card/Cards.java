package domain.card;

import domain.participant.Score;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private static final Score BUST_BOUNDARY_EXCLUSIVE = new Score(21);
    private static final int INITIAL_CARDS_SIZE = 2;
    private final List<Card> cards;

    public Cards() {
        this.cards = Collections.emptyList();
    }

    public Cards(final List<Card> cards) {
        this.cards = Collections.unmodifiableList(cards);
    }

    public Cards receiveInitialCards(final List<Card> initialCards) {
        if (initialCards.size() == INITIAL_CARDS_SIZE) {
            List<Card> newCards = new ArrayList<>(initialCards);
            return new Cards(newCards);
        }
        throw new IllegalArgumentException("처음 받는 카드는 2장이어야 합니다.");
    }

    public Cards receiveCard(Card card) {
        List<Card> newCards = new ArrayList<>(cards);
        newCards.add(card);
        return new Cards(newCards);
    }

    public boolean isBust() {
        return calculateScore().isGreaterThan(BUST_BOUNDARY_EXCLUSIVE);
    }

    public Score calculateScore() {
        boolean hasAce = hasAce();
        Score defaultScore = new Score(calculateDefaultScore());

        if (hasAce) {
            defaultScore = defaultScore.applyBonusScore();
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
