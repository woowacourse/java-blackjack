package blackjack.domain.state;

import blackjack.domain.card.Deck;

public class InitialParticipantState extends ParticipantState {

    public InitialParticipantState() {
        super(Hand.of());
    }

    @Override
    public ParticipantState draw(Deck deck) {
        Hand hand = Hand.of(deck.draw(), deck.draw());
        if (hand.isBlackJack()) {
            return new BlackJackParticipantState(hand);
        }
        return new HitParticipantState(hand);
    }

    @Override
    public ParticipantState stand() {
        throw new UnsupportedOperationException("초기 상태에서는 할 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double getProfitRate(Hand other) {
        throw new UnsupportedOperationException("초기 상태에서는 할 수 없습니다.");
    }

    @Override
    public Hand getHand() {
        throw new UnsupportedOperationException("초기 상태에서는 할 수 없습니다.");
    }
}
