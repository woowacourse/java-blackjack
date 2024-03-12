package blackjack.player.state;

import blackjack.card.Card;
import blackjack.player.Hand;

public class TerminatedState extends PlayerState {

    protected TerminatedState(Hand hand) {
        super(hand);
    }

    @Override
    public PlayerState drawCard(Card card) {
        throw new UnsupportedOperationException("현재 상태에서는 드로우 여부를 결정할 수 없습니다.");
    }

    @Override
    public PlayerState stand() {
        throw new UnsupportedOperationException("현재 상태에서는 스탠드 여부를 결정할 수 없습니다.");
    }
}
