package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
    private static final int MIN_SUM_FOR_ACE_IS_ONE = 12;
    public static final int BLACKJACK_SCORE = 21;
    public static final int MAX_SUM_FOR_DEALER_MORE_CARD = 16;

    private List<Card> cards = new ArrayList<>();

    public void put(Card card) {
        this.cards.add(card);
    }

    public void put(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public int sumScores() {
        int score = sumScoresAceAsOne();
        if (hasAce()) {
            score = calculateOptimalSumWhenAceExists(score);
        }
        return score;
    }

    private int sumScoresAceAsOne() {
        return cards.stream()
            .mapToInt(Card::getSymbolScore)
            .sum();
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    private int calculateOptimalSumWhenAceExists(int scoreAceAsOne) {
        if (scoreAceAsOne < MIN_SUM_FOR_ACE_IS_ONE) {
            scoreAceAsOne -= Symbol.ACE.getScore();
            return scoreAceAsOne + Symbol.ALTERNATE_ACE_SCORE;
        }
        return scoreAceAsOne;
    }

    public boolean isBlackJack() {
        return sumScores() == BLACKJACK_SCORE;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(this.cards);
    }

    public boolean isLargerThan(int score) {
        return this.sumScores() > score;
    }

    public boolean isSmallerThan(int score) {
        return this.sumScores() < score;
    }
}
