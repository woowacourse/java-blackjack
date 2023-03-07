package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer extends Participant {

    private static final int CARD_RECEIVE_CRITERIA = 16;
    private static final int MAX_CARD_COUNT = 3;
    private static final int FIRST_CARD_INDEX = 0;

    public Dealer() {
        super();
    }

    @Override
    public boolean canReceive() {
        return isUnderScore() && isUnderCount();
    }

    public Card getFirst() {
        List<Card> hand = this.hand.getHand();
        if (hand.isEmpty()) {
            throw new IllegalStateException("잘못된 접근입니다.");
        }
        return hand.get(FIRST_CARD_INDEX);
    }

    private boolean isUnderCount() {
        return hand.getCount() < MAX_CARD_COUNT;
    }

    private boolean isUnderScore() {
        Score score = calculateScore();
        Score receiveScore = new Score(CARD_RECEIVE_CRITERIA);
        return score.isLessThan(receiveScore) || score.equals(receiveScore);
    }
}
