package blackjack.domain;

import static blackjack.domain.Denomination.*;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private static final int BONUS_ACE_ADD_SCORE = 10;

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
        return convertCloseBlackJack(ace, totalScore);
    }

    private int convertCloseBlackJack(boolean aceCheck, int totalScore) {
        if (aceCheck) {
            return calculateAceScore(totalScore);
        }
        return totalScore;
    }

    private int calculateAceScore(int score) {
        if ((score + BONUS_ACE_ADD_SCORE) > BLACKJACK_SCORE) {
            return score;
        }
        return score + BONUS_ACE_ADD_SCORE;
    }

    public void add(Card card) {
        value.add(card);
    }

    public List<Card> getValue() {
        return List.copyOf(value);
    }
}
