package blackjack.model.state;

import blackjack.model.participant.Hand;
import blackjack.model.card.CardDeck;

public class BlackjackState extends State {

    public BlackjackState(Hand hand) {
        super(hand);
    }

    @Override
    public State draw(CardDeck cardDeck) {
        throw new IllegalStateException("블랙잭 상태에서는 카드를 더 뽑을 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public boolean isBlackjack() {
        return true;
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public boolean isStand() {
        return false;
    }
}
