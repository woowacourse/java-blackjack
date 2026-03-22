package domain.card;

import domain.state.HandState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private static final int ACE_ADJUST_SCORE = 10;
    private static final int INITIAL_CARD_COUNT = 2;
    private static final int BLACKJACK_SCORE = 21;

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
        final int score = calculateScore();
        if (score > BLACKJACK_SCORE) {
            return HandState.BUST;
        }

        if (score < BLACKJACK_SCORE) {
            return HandState.STAND;
        }

        if (isInitialCards()) {
            return HandState.BLACKJACK;
        }
        return HandState.STAND;
    }

    private boolean isInitialCards(){
        return cards.size() == INITIAL_CARD_COUNT;
    }

    public boolean canDraw(){
        return getScore() < BLACKJACK_SCORE;
    }
}
