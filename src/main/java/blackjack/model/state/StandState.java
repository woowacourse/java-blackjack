package blackjack.model.state;

import blackjack.model.card.CardDeck;
import blackjack.model.participant.Hand;

public class StandState extends FinalState {

    public StandState(Hand hand) {
        super(hand);
    }

    @Override
    public State draw(CardDeck cardDeck) {
        throw new IllegalStateException("스탠드 상태에서는 카드를 더 뽑을 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public double getProfitWeight() {
        return 1;
    }
}
