package blackjack.model.state.running;

import blackjack.model.card.Card;
import blackjack.model.state.Hand;
import blackjack.model.state.State;
import blackjack.model.state.finished.Blackjack;

public final class InitialDeal extends RunningState {

    private InitialDeal(Hand hand) {
        super(hand);
    }

    public InitialDeal() {
        this(new Hand());
    }

    @Override
    public State receiveCard(Card card) {
        Hand newHand = hand.add(card);
        if (newHand.size() == ONE) {
            return new InitialDeal(newHand);
        }
        if (newHand.size() == BLACKJACK_CARD_COUNT && newHand.getTotal() == MAX_SCORE) {
            return new Blackjack(newHand);
        }
        return new Hit(newHand);
    }

    @Override
    public State stand() {
        throw new IllegalArgumentException("처음부터 바로 스탠드할 수 없습니다.");
    }
}
