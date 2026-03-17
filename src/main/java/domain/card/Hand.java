package domain.card;

import domain.state.HandState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private static final int ACE_ADJUST_SCORE = 10;
    private static final int INITIAL_CARD_COUNT = 2;
    private static final int BLACKJACK_SCORE = 21;
    private static final int DEALER_DRAW_SCORE = 16;

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public int getScore() {
        return calculateScore();
    }

    public boolean checkBust() {
        return calculateScore() > BLACKJACK_SCORE;
    }

    public boolean isDealerDrawScore() {
        return calculateScore() <= DEALER_DRAW_SCORE;
    }

    private int calculateScore() {
        int totalScore = cards.stream()
                .mapToInt(Card::getScore)
                .sum();
        int aceCount = countAces();
        while (totalScore > BLACKJACK_SCORE && aceCount > 0) {
            totalScore -= ACE_ADJUST_SCORE;
            aceCount--;
        }
        return totalScore;
    }

    private int countAces() {
        return (int) cards.stream().filter(Card::isAce).count();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public HandState getHandState(){
        return HandState.getState(calculateScore(), isInitialCards());
    }

    private boolean isInitialCards(){
        return cards.size() == INITIAL_CARD_COUNT;
    }
}
