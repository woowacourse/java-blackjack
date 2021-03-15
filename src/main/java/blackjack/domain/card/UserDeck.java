package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class UserDeck {

    private static final int ACE_CALIBRATION = 10;
    public static final int BLACK_JACK_NUMBER = 21;

    private final List<Card> userCards = new ArrayList<>();

    public void draw(Card card) {
        userCards.add(card);
    }

    public int score() {
        int score = calculateScore();
        int aceCount = getAceCount();
        while (aceCount != 0 && score > BLACK_JACK_NUMBER) {
            score -= ACE_CALIBRATION;
            aceCount -= 1;
        }
        return score;
    }

    private int getAceCount() {
        return (int) userCards.stream()
            .filter(Card::isAce)
            .count();
    }

    private int calculateScore() {
        return userCards.stream()
            .mapToInt(Card::getCardValue)
            .sum();
    }

    public List<Card> getUserCards() {
        return userCards;
    }

    public boolean isBlackjack() {
        return score() == BLACK_JACK_NUMBER && getUserCards().size() == 2;
    }

    public boolean isBust() {
        return score() > BLACK_JACK_NUMBER;
    }
}