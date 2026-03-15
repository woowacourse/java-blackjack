package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private static final int ACE_ADJUST_SCORE = 10;
    private static final int INITIAL_CARD_COUNT = 2;

    private final List<Card> cards;
    private Score score;
    private AceCount aceCount;

    public Hand() {
        this.cards = new ArrayList<>();
        this.score = Score.zero();
        this.aceCount = AceCount.zero();
    }

    public void addCard(Card card) {
        this.cards.add(card);
        score = score.addScore(card.getScore());
        if (card.isAce()) {
            aceCount = aceCount.increase();
        }
        adjustAceScore();
    }

    public Score getScore() {
        return score;
    }

    public int getResult() {
        return score.getScore();
    }

    public boolean checkBust() {
        return score.isBust();
    }

    private void adjustAceScore() {
        while (score.isBust() && aceCount.hasAny()) {
            score = score.subScore(ACE_ADJUST_SCORE);
            aceCount = aceCount.decrease();
        }
    }

    public int size() {
        return cards.size();
    }

    public Card get(int index) {
        return cards.get(index);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public HandState getHandState(){
        return HandState.getState(score.getScore(), isInitialCards());
    }

    private boolean isInitialCards(){
        return cards.size() == INITIAL_CARD_COUNT;
    }
}
