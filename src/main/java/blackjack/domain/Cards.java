package blackjack.domain;

import static blackjack.domain.Denomination.*;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private static final int BONUS_ACE_ADD_SCORE = -10;

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
        if (score > BLACKJACK_SCORE) {
            return score + BONUS_ACE_ADD_SCORE;
        }
        return score;
    }

    public void add(Card card) {
        value.add(card);
    }

    public boolean isBlackJack() {
        return value.size() == 2 && calculateTotalScore() == 21;
    }

    public List<Card> getValue() {
        return List.copyOf(value);
    }
}
