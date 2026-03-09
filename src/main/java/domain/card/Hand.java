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
        return Score.calculate(this);
    }

    public boolean isBlackJack() {
        return hand.size() == INIT_HAND_SIZE && calculateSum() == BLACKJACK_SCORE;
    }

    public List<Card> getHand() {
        return hand;
    }
}
