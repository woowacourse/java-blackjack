package domain.participant;

import static domain.participant.Dealer.DEALER_NAME;

import constants.ErrorCode;
import domain.BetAmount;
import exception.ReservedPlayerNameException;

public class Player extends Participant {

    private final BetAmount betAmount;

    public Player(final Name name, final Hands hands) { // TODO 이전 테스트를 위한 생성자
        super(name, hands);
        validate(name);
        this.betAmount = new BetAmount(10);
    }

    public Player(final Name name, final BetAmount betAmount) {
        super(name, Hands.createEmptyHands());
        this.betAmount = betAmount;
    }

    private void validate(final Name name) {
        if (DEALER_NAME.equals(name)) {
            throw new ReservedPlayerNameException(ErrorCode.RESERVED_NAME);
        }
    }

    public BetAmount getBetAmount() {
        return betAmount;
    }

    @Override
    public boolean canDeal() {
        return handsSum() < Hands.BLACK_JACK;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
