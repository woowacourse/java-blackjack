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

    public Card getFirstCard() {
        List<Card> cards = this.hand.getCards();
        if (cards.isEmpty()) {
            throw new IllegalStateException("보유하고 있는 카드가 없습니다.");
        }
        return cards.get(FIRST_CARD_INDEX);
    }

    private boolean isUnderCount() {
        return hand.getCount() < MAX_CARD_COUNT;
    }

    private boolean isUnderScore() {
        return calculateCurrentScore() <= CARD_RECEIVE_CRITERIA;
    }
}
