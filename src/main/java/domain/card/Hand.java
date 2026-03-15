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
    private int score;

    public Hand() {
        this.cards = new ArrayList<>();
        this.score = 0;
    }

    public void addCard(Card card) {
        this.cards.add(card);
        score += card.getScore();
        adjustAceScore();
    }

    public int getScore() {
        return score;
    }

    public int getResult() {
        return score;
    }

    public boolean checkBust() {
        return score > BLACKJACK_SCORE;
    }

    public boolean isDealerDrawScore() {
        return score <= DEALER_DRAW_SCORE;
    }

    private void adjustAceScore() {
        int aceCount = countAces();
        while (checkBust() && aceCount > 0) {
            score -= ACE_ADJUST_SCORE;
            aceCount--;
        }
    }

    private int countAces() {
        return (int) cards.stream().filter(Card::isAce).count();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public HandState getHandState(){
        return HandState.getState(score, isInitialCards());
    }

    private boolean isInitialCards(){
        return cards.size() == INITIAL_CARD_COUNT;
    }
}
