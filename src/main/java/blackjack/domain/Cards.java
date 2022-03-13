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
            totalScore = card.getSumScore(totalScore);
        }
        return ConvertCloseBlackJack(totalScore);
    }

    private int ConvertCloseBlackJack(int totalScore) {
        int convertScore = totalScore;

        for (int i = 0; i < countAce(); i++) {
            convertScore = calculateAceScore(convertScore);
        }
        return convertScore;
    }

    private Long countAce() {
        return value.stream()
                .map(Card::getDenomination)
                .filter(card -> ACE.equals(card))
                .count();
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
