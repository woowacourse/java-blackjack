package domain.gamer;

import domain.card.Card;
import domain.card.CardNumber;

public class Result {
    public static final int BUST_NUMBER = 22;
    public static final int ACE_HIDDEN_SCORE = 10;

    public int calculateWithAce(Gamer gamer) {
        int score = calculateScore(gamer);
        if (gamer.isContainAce() && score + ACE_HIDDEN_SCORE < BUST_NUMBER) {
            return score + ACE_HIDDEN_SCORE;
        }
        return score;
    }

    private int calculateScore(Gamer gamer) {
        return gamer.getCards()
                .stream()
                .map(Card::getCardNumber)
                .mapToInt(CardNumber::getScore)
                .sum();
    }
}
