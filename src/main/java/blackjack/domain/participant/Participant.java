package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;

public abstract class Participant {

    protected static final int BLACK_JACK_SCORE = 21;
    protected static final int BLACK_JACK_CARD_COUNT = 2;

    protected Hand hand;

    protected Participant() {
        this.hand = new Hand();
    }

    public void drawCard(final Card card) {
        if (!isDrawable()) {
            throw new IllegalStateException("더 이상 카드를 뽑을 수 없습니다.");
        }
        hand = hand.addCard(card);
    }

    public boolean isBlackJack() {
        return hand.count() == BLACK_JACK_CARD_COUNT && hand.calculateTotalScore() == BLACK_JACK_SCORE;
    }

    public int getScore() {
        return hand.calculateTotalScore();
    }

    public Hand getHand() {
        return hand;
    }

    public abstract boolean isDrawable();

    public abstract boolean isDealer();
}
