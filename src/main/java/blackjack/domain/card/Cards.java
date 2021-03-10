package blackjack.domain.card;

import blackjack.domain.game.WinOrLose;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private final List<Card> cards = new ArrayList<>();

    public void add(final Card card) {
        cards.add(card);
    }

    public Score calculateScore() {
        Score score = Score.ZERO_SCORE;
        for (Card card : cards) {
            score = score.addScore(card.getScore());
        }
        return decideAceScore(score);
    }

    private Score decideAceScore(Score score) {
        if (hasAce() && !isBust(score.useAceAsEleven())) {
            return score.useAceAsEleven();
        }
        return score;
    }

    public WinOrLose compareCardsScore(Cards other) {
        Score thisScore = calculateScore();
        Score otherScore = other.calculateScore();

        if (thisScore.isBust() && !otherScore.isBust()) {
            return WinOrLose.LOSE;
        }

        if (!thisScore.isBust() && otherScore.isBust()) {
            return WinOrLose.WIN;
        }

        if (thisScore.isHigherScore(otherScore)) {
            return WinOrLose.WIN;
        }

        if (otherScore.isLowerScore(thisScore)) {
            return WinOrLose.LOSE;
        }

        return WinOrLose.DRAW;
    }

    private boolean hasAce() {
        return cards.stream()
                .filter(card -> card.isAce())
                .findFirst()
                .isPresent();
    }

    public boolean isBust(Score score) {
        return score.isBust();
    }

    public boolean isBust() {
        return isBust(calculateScore());
    }

    public boolean isBlackJack() {
        return calculateScore().isBlackJack();
    }

    public List<Card> cards() {
        return Collections.unmodifiableList(cards);
    }
}
