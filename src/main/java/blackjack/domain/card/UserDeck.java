package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class UserDeck {

    private static final int ACE_CALIBRATION = 10;
    public static final int BLACK_JACK_NUMBER = 21;
    public static final int BURST_CONDITION = 0;

    private final List<Card> userCards = new ArrayList<>();

    public void add(Card card) {
        userCards.add(card);
    }

    public int score() {
        int originalScore = calculateOriginalScore();
        int aceCount = getAceCount();
        while (aceCount != 0 && originalScore > BLACK_JACK_NUMBER) {
            originalScore -= ACE_CALIBRATION;
            aceCount -= 1;
        }
        if (originalScore > BLACK_JACK_NUMBER) {
            return BURST_CONDITION;
        }
        return originalScore;
    }

    public boolean isBust() {
        if (score() == BURST_CONDITION) {
            return true;
        }
        return false;
    }

    private int getAceCount() {
        return (int) userCards.stream()
            .filter(Card::isAce)
            .count();
    }

    private int calculateOriginalScore() {
        return userCards.stream()
            .mapToInt(Card::getPoint)
            .sum();
    }

    public List<Card> getUserCards() {
        return userCards;
    }
}
