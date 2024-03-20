package blackjack.domain.state;

public class BustParticipantState extends ClosedParticipantState {

    BustParticipantState(Hand hand) {
        super(hand);
    }

    @Override
    public double getProfitRate(Hand other) {
        return -1;
    }
}
