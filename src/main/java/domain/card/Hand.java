package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hand {

    public static final int BUST_BOUND = 21;

    private final List<Card> hand;
    private final Set<Integer> scores = new HashSet<>();

    public Hand() {
        this.hand = new ArrayList<>();
    }

    public void addCard(final Card card) {
        hand.add(card);
        updateScore(card);
    }


    public boolean isBust() {
        return getScore() > BUST_BOUND;
    }

    public boolean isBlackjack() {
        return hand.size() == 2 && getScore() == BUST_BOUND;
    }


    public int getScore() {
        int notBustMaxScore = 0; // 21 이하 중에 최대 점수

        if (scores.isEmpty()) {
            return 0;
        }

        for (final int score : scores) {
            notBustMaxScore = getNotBustMaxScore(score, notBustMaxScore);
        }

        if (notBustMaxScore != 0) { // 없다면, 제일 작은거
            return notBustMaxScore;
        }

        return Collections.min(scores);
    }


    private void updateScore(final Card card) {
        final HashSet<Integer> newScore = new HashSet<>();

        if (scores.isEmpty()) {
            scores.addAll(card.getCardRank().getScores());
            return;
        }
        for (final Integer score : scores) {
            addScore(card, score, newScore);
        }

        scores.clear();
        scores.addAll(newScore);
    }

    private static void addScore(final Card card, final Integer score, final HashSet<Integer> newScore) {
        for (final int s : card.getCardRank().getScores()) {
            newScore.add(score + s);
        }
    }

    private static int getNotBustMaxScore(final int score, int notBustMaxScore) {
        if (score <= BUST_BOUND && score > notBustMaxScore) {
            notBustMaxScore = score;
        }
        return notBustMaxScore;
    }

    public List<Card> getHand() {
        return List.copyOf(hand);
    }
}
