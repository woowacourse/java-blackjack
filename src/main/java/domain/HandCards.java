package domain;

import static constant.BlackjackConstant.BUST_BOUND;
import static domain.CardRank.ACE;
import static domain.CardRank.JACK;
import static domain.CardRank.KING;
import static domain.CardRank.QUEEN;
import static domain.CardRank.TEN;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// FIXME: Hand만으로도 네이밍 충분
public class HandCards {

    private final List<Card> handCards;
    private final Set<Integer> scores = new HashSet<>();

    public HandCards(List<Card> handCards) {
        this.handCards = handCards;
    }

    public void addCard(Card card) {
        handCards.add(card);
        updateScore(card);
    }

    private void updateScore(Card card) {
        HashSet<Integer> newScore = new HashSet<>();

        if (scores.isEmpty()) {
            scores.addAll(card.getCardRank().getScores());
            return;
        }
        for (Integer score : scores) {
            addScore(card, score, newScore);
        }

        scores.clear();
        scores.addAll(newScore);
    }

    private static void addScore(Card card, Integer score, HashSet<Integer> newScore) {
        for (int s : card.getCardRank().getScores()) {
            newScore.add(score + s);
        }
    }


    public boolean isBust() {
        return getScore() > BUST_BOUND;
    }

    public boolean isBlackjack() {
        List<CardRank> cardRanks = handCards.stream()
                .map(Card::getCardRank)
                .toList();

        return cardRanks.contains(ACE) &&
                (cardRanks.contains(TEN) || cardRanks.contains(JACK)
                        || cardRanks.contains(QUEEN) || cardRanks.contains(KING));
    }


    public int getScore() {
        int notBustMaxScore = 0; // 21 이하 중에 최대 점수
        for (int score : scores) {
            notBustMaxScore = getNotBustMaxScore(score, notBustMaxScore);
        }

        if (notBustMaxScore != 0) { // 없다면, 제일 작은거
            return notBustMaxScore;
        }

        return Collections.min(scores);
    }

    private static int getNotBustMaxScore(int score, int notBustMaxScore) {
        if (score <= BUST_BOUND && score > notBustMaxScore) {
            notBustMaxScore = score;
        }
        return notBustMaxScore;
    }

    public List<Card> getHandCards() {
        return List.copyOf(handCards);
    }
}