package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

public class HitParticipantState extends ParticipantState {

    HitParticipantState(Hand hand) {
        super(hand);
    }

    @Override
    public ParticipantState draw(Deck deck) {
        Card card = deck.draw();
        Hand newHand = getHand().add(card);
        if (newHand.isBust()) {
            return new BustParticipantState(newHand);
        }
        return new HitParticipantState(newHand);
    }

    @Override
    public ParticipantState stand() {
        return new StandParticipantState(getHand());
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double getProfitRate(Hand other) {
        throw new UnsupportedOperationException("현재 상태에서는 할 수 없습니다.");
    }
}
