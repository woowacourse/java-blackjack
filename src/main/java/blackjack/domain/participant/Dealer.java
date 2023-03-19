package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer extends Participant {

    private static final Score CARD_RECEIVE_CRITERIA = new Score(16);
    private static final int FIRST_CARD_INDEX = 0;

    public Dealer() {
        super();
    }

    @Override
    public boolean canReceive() {
        return isUnderScore();
    }

    public Card getFirst() {
        List<Card> hand = this.hand.getHand();
        if (hand.isEmpty()) {
            throw new IllegalStateException("잘못된 접근입니다.");
        }
        return hand.get(FIRST_CARD_INDEX);
    }

    private boolean isUnderScore() {
        Score score = calculateScore();
        return score.isLessThan(CARD_RECEIVE_CRITERIA) || score.equals(CARD_RECEIVE_CRITERIA);
    }
}
