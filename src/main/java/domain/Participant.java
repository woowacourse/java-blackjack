package domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Participant {

    private final int BLACKJACK_NUM = 21;
    private final int DEALER_HIT_NUM = 16;

    protected final List<Card> hand = new ArrayList<>();

    public void receiveInitCard(List<Card> cards) {
        hand.addAll(cards);
    }

    public void receiveCard(Card card) {
        hand.add(card);
    }

    public boolean isBlackjack(int score) {
        return score == BLACKJACK_NUM;
    }

    public boolean isBurst(int score) {
        return score > BLACKJACK_NUM;
    }

    public boolean isHit(int score) {
        return score <= DEALER_HIT_NUM;
    }

    public List<Card> getHand() {
        return hand;
    }
}
