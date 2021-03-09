package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static blackjack.domain.card.Score.*;

public class Cards {

    private final List<Card> cards;

    public Cards() {
        cards = new ArrayList<>();
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public Score totalScore() {
        Score totalScore = ZERO_SCORE;
        for (Card card : cards) {
            Score cardScore = card.getCardScore();
            totalScore = totalScore.addScore(cardScore);
        }

        if (hasAce() && totalScore.addScore(ADDITIONAL_ACE_SCORE)
                .isLessThan(Score.of(MIN_BUST_SCORE))) {
            totalScore = totalScore.addScore(ADDITIONAL_ACE_SCORE);
        }
        return totalScore;
    }

    public int countCards() {
        return cards.size();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public boolean isBlackJack() {
        return isTwentyOne() && isOnlyTwoCard();
    }

    public boolean isTwentyOne() {
        return totalScore().isTwentyOne();
    }

    public boolean isOnlyTwoCard() {
        return countCards() == 2;
    }

    public boolean isBust() {
        return totalScore().isBust();
    }

    public boolean isBiggerThan(Cards cards) {
        return totalScore().isBiggerThan(cards.totalScore());
    }

    public boolean isLessThan(Cards cards) {
        return totalScore().isLessThan(cards.totalScore());
    }
}
