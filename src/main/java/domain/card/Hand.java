package domain.card;

import static constant.BlackjackConstant.BLACKJACK_CARD_COUNT;
import static constant.BlackjackConstant.BUST_BOUND;
import static domain.card.CardRank.ACE;
import static domain.card.CardRank.JACK;
import static domain.card.CardRank.KING;
import static domain.card.CardRank.QUEEN;
import static domain.card.CardRank.TEN;

import java.util.*;

public class Hand {

    private final List<Card> hands;
    private final Set<Integer> scores = new HashSet<>();

    public Hand(List<Card> hands) {
        this.hands = new ArrayList<>(hands);
        for (Card card : hands) {
            updateScore(card);
        }
    }

    public void addCard(Card card) {
        hands.add(card);
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
        if(hands.size() != BLACKJACK_CARD_COUNT) {
            return false;
        }

        List<CardRank> cardRanks = hands.stream()
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

        if (notBustMaxScore != 0) {
            return notBustMaxScore;
        }

        return Collections.min(scores); // // 21이하의 수가 없다면, 제일 작은거 리턴하기
    }

    private static int getNotBustMaxScore(int score, int notBustMaxScore) {
        if (score <= BUST_BOUND && score > notBustMaxScore) {
            notBustMaxScore = score;
        }
        return notBustMaxScore;
    }

    public List<Card> getHands() {
        return List.copyOf(hands);
    }

    public int getSize() {
        return hands.size();
    }
}
