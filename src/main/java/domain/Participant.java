package domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Participant {

    private final int BLACKJACK_NUM = 21;

    protected final List<Card> hand = new ArrayList<>();

    public void receiveInitCard(List<Card> cards) {
        hand.addAll(cards);
    }

    public void receiveCard(Card card) {
        hand.add(card);
    }

    public int calculateScore() {
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
        return score;
    }

    public boolean isBlackjack() {
        int score = calculateScore();
        return score == BLACKJACK_NUM && hand.size() == 2;
    }

    public boolean isBurst(int score) {
        return score > BLACKJACK_NUM;
    }

    public List<Card> getHand() {
        return hand;
    }

    private int calculateOptimalAceScore(int sum) {
        if (sum > 10) {
            return 1;
        }
        return 11;
    }
}
