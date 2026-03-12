package domain.player;

import domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

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
        int baseScore = hand.stream()
                .mapToInt(Card::getValue)
                .sum();
        int aceCount = (int) hand.stream()
                .filter(Card::isAce)
                .count();
        this.score = IntStream.range(0, aceCount)
                .reduce(baseScore, (currentScore, index) -> currentScore + getAceBonus(currentScore));
    }

    public int getAceBonus(int sum) {
        if (sum > 11) {
            return 0;
        }
        return 10;
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
