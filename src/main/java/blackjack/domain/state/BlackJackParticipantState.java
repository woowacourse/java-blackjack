package blackjack.domain.state;

public class BlackJackParticipantState extends ClosedParticipantState {

    BlackJackParticipantState(Hand hand) {
        super(hand);
    }

    @Override
    public double getProfitRate(Hand other) {
        if (other.isBlackJack()) {
            return 1;
        }
        return 1.5;
    }
}
