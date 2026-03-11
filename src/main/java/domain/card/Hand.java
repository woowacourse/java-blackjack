package domain.card;

import static domain.BlackjackGame.BUST_BOUND;
import static domain.card.CardRank.ACE;
import static domain.card.CardRank.JACK;
import static domain.card.CardRank.KING;
import static domain.card.CardRank.QUEEN;
import static domain.card.CardRank.TEN;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hand {

    private final List<Card> hand;
    private final Set<Integer> scores = new HashSet<>();

    public Hand(final List<Card> hand) {
        this.hand = new ArrayList<>(hand);
    }

    public void addCard(final Card card) {
        hand.add(card);
        updateScore(card);
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


    public boolean isBust() {
        return getScore() > BUST_BOUND;
    }

    public boolean isBlackjack() {
        final List<CardRank> cardRanks = hand.stream()
                .map(Card::getCardRank)
                .toList();

        return cardRanks.contains(ACE) &&
                (cardRanks.contains(TEN) || cardRanks.contains(JACK)
                        || cardRanks.contains(QUEEN) || cardRanks.contains(KING));
    }


    public int getScore() {
        int notBustMaxScore = 0; // 21 이하 중에 최대 점수
        for (final int score : scores) {
            notBustMaxScore = getNotBustMaxScore(score, notBustMaxScore);
        }

        if (notBustMaxScore != 0) { // 없다면, 제일 작은거
            return notBustMaxScore;
        }

        return Collections.min(scores);
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
