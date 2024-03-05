package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

class Hand {

    private static final int MAX_ACE_SCORE = 11;
    private static final int BLACKJACK_SCORE = 21;

    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int notAceScore = calculateNotAceScore();
        int aceCount = getAceCount();

        return calculateScoreWithAce(notAceScore, aceCount);
    }

    private int calculateNotAceScore() {
        return cards.stream()
                .map(Card::getCardRank)
                .filter(CardRank::isNotAce)
                .mapToInt(CardRank::getScore)
                .sum();
    }

    private int getAceCount() {
        return (int) cards.stream()
                .map(Card::getCardRank)
                .filter(CardRank::isAce)
                .count();
    }

    private int calculateScoreWithAce(int notAceScore, int aceCount) {
        int scoreWithAce = notAceScore;

        for (int i = 0; i < aceCount; i++) {
            scoreWithAce += decideAceScore(scoreWithAce);
        }

        return scoreWithAce;
    }

    private int decideAceScore(int score) {
        if (isBust(score + MAX_ACE_SCORE)) {
            return CardRank.ACE.getScore();
        }

        return MAX_ACE_SCORE;
    }

    private boolean isBust(int score) {
        return score > BLACKJACK_SCORE;
    }

    public List<Card> getCards() {
        return cards;
    }
}
