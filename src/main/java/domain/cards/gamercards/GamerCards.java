package domain.cards.gamercards;

import domain.cards.Card;
import domain.cards.cardinfo.CardNumber;

import java.util.Collections;
import java.util.List;

public class GamerCards {

    private static final int BUST_THRESHOLD = 21;
    private static final int CONDITION_DECIDING_A_SCORE = 10;
    private static final int A_SCORE_GAP = 10;

    protected final List<Card> cards;

    public GamerCards(List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int scoreExceptAce = sumExceptAceCards();
        return sumAceCards(scoreExceptAce);
    }

    private int sumExceptAceCards() {
        return cards.stream()
                .filter(Card::isNotAce)
                .mapToInt(Card::score)
                .sum();
    }

    private int sumAceCards(int score) {
        int currentScore = score;
        for (Card card : cards) {
            currentScore = addAceScore(card, currentScore);
        }
        return currentScore;
    }

    private int addAceScore(Card card, int currentScore) {
        if (card.isAce()) {
            return currentScore + decideAceScore(currentScore);
        }
        return currentScore;
    }

    private int decideAceScore(int currentScore) {
        if (currentScore <= CONDITION_DECIDING_A_SCORE) {
            return CardNumber.A.getScore() + A_SCORE_GAP;
        }
        return CardNumber.A.getScore();
    }

    public boolean hasScoreUnderBustThreshold() {
        return calculateScore() <= BUST_THRESHOLD;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
