package domain.card;

import java.util.ArrayList;
import java.util.List;

import static util.BlackJackConstant.*;

public class Hand {

    private final List<Card> hand = new ArrayList<>();

    public Hand() {}

    public void drawCard(Card card) {
        this.hand.add(card);
    }

    public boolean isBust() {
        return calculateSum() > BLACKJACK_SCORE;
    }

    public int calculateSum() {
        int sum = 0;
        int aceCount = 0;

        for (Card card : hand) {
            sum += card.getScore();
            if (card.isAce()) {
                aceCount++;
            }
        }

        sum = handleAce(sum, aceCount);

        return sum;
    }

    public boolean isBlackJack() {
        return hand.size() == INIT_HAND_SIZE && calculateSum() == BLACKJACK_SCORE;
    }

    public List<Card> getHand() {
        return hand;
    }

    private int handleAce(int sum, int aceCount) {
        while (sum > BLACKJACK_SCORE && aceCount > 0) {
            aceCount--;
            sum -= ACE_ADJUSTMENT_VALUE;
        }
        return sum;
    }
}
