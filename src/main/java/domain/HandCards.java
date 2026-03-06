package domain;

import static domain.CardRank.ACE;
import static domain.CardRank.JACK;
import static domain.CardRank.KING;
import static domain.CardRank.QUEEN;
import static domain.CardRank.TEN;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HandCards {

    private static final int BUST_BOUND = 21;
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
            for (int s : card.getCardRank().getScores()) {
                newScore.add(score + s);
            }
        }

        scores.clear();
        scores.addAll(newScore);
    }

    public int getScore() {
        // 21 이하 중에 최대 점수
        int notBustMaxScore = 0;
        for (int score : scores) {
            if (score <= BUST_BOUND && score > notBustMaxScore) {
                notBustMaxScore = score;
            }
        }

        // 없다면, 제일 작은거
        if (notBustMaxScore != 0) {
            return notBustMaxScore;
        }

        return Collections.min(scores);
    }

    public boolean isBust(int total) {
        return total > BUST_BOUND;
    }

    public List<Card> getHandCards() {
        return List.copyOf(handCards);
    }

    public boolean isBlackjack() {
        List<CardRank> cardRanks = handCards.stream()
                .map(Card::getCardRank)
                .toList();

        return cardRanks.contains(ACE) &&
                (cardRanks.contains(TEN) || cardRanks.contains(JACK)
                        || cardRanks.contains(QUEEN) || cardRanks.contains(KING));
    }

    private boolean hasAce() {
        for (Card handCard : handCards) {
            if (handCard.getCardRank().equals(ACE)) {
                return true;
            }
        }
        return false;
    }
}