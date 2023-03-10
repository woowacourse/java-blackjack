package blackjack.model.state;

import blackjack.model.card.CardDeck;
import blackjack.model.participant.Hand;

public class BlackjackState extends FinalState {

    public BlackjackState(Hand hand) {
        super(hand);
    }

    @Override
    public State draw(CardDeck cardDeck) {
        throw new IllegalStateException("블랙잭 상태에서는 카드를 더 뽑을 수 없습니다.");
    }

    @Override
    public double getProfitWeight() {
        return 1.5;
    }

}
