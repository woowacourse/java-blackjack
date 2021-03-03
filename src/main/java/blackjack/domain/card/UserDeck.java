package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class UserDeck {

    private static final int ACE_CALIBRATION = 10;
    private static final int BLACK_JACK_NUMBER = 21;
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
        return originalScore;
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
}
