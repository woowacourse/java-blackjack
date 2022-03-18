package blackjack.domain;

import static blackjack.domain.Denomination.*;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private static final int BONUS_ACE_CONVERT_SCORE = 10;
    private static final int BLACKJACK_CARD_SIZE = 2;

    private final List<Card> value;

    public Cards(List<Card> cards) {
        this.value = new ArrayList<>(cards);
    }

    public int calculateTotalScore() {
        int totalScore = 0;

        for (Card card : value) {
            totalScore = card.addScore(totalScore);
        }

        boolean ace = value.stream().anyMatch(Card::isAceCard);
        return convertCloseWin(ace, totalScore);
    }

    private int convertCloseWin(boolean aceCheck, int totalScore) {
        if (aceCheck) {
            return calculateAceScore(totalScore);
        }
        return totalScore;
    }

    private int calculateAceScore(int score) {
        if (score + BONUS_ACE_CONVERT_SCORE > BLACKJACK_SCORE) {
            return score;
        }
        return score + BONUS_ACE_CONVERT_SCORE;
    }

    public void add(Card card) {
        value.add(card);
    }

    public boolean isBlackJack() {
        return value.size() == BLACKJACK_CARD_SIZE && calculateTotalScore() == BLACKJACK_SCORE;
    }

    public List<Card> getValue() {
        return List.copyOf(value);
    }
}
