package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public abstract class Player {

    private static final int HIT_SCORE = 16;
    private static final int BLACKJACK_SCORE = 21;

    private final List<Card> hand = new ArrayList<>();
    private int score = 0;

    public void receiveInitCard(List<Card> cards) {
        hand.addAll(cards);
    }

    public int getScore() {
        return score;
    }

    public void receiveCard(Card card) {
        hand.add(card);
    }

    public List<Card> getHand() {
        return Collections.unmodifiableList(hand);
    }

    public void calculateScore() {
        int score = 0;
        int aceCount = 0;
        for (Card card: hand) {
            if (!card.isAce()) {
                score += card.getValue();
                continue;
            }
            aceCount++;
        }
        for(int i = 0; i< aceCount; i++){
            score += calculateOptimalAceScore(score);
        }
        this.score = score;
    }

    public int calculateOptimalAceScore(int sum) {
        if (sum > 10) {
            return 1;
        }
        return 11;
    }

    public boolean isBurst() {
        if(score > BLACKJACK_SCORE) {
            return true;
        }
        return false;
    }

    public boolean isHit() {
        if (score <= HIT_SCORE) {
            return true;
        }
        return false;
    }

    public boolean isBlackjack() {
        return hand.size() == 2 && score == BLACKJACK_SCORE;
    }
}
