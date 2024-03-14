package domain.participant;

import static domain.participant.Dealer.DEALER_NAME;

import constants.ErrorCode;
import exception.ReservedPlayerNameException;

public class Player extends Participant {

    // TODO: Hands 로 레포지토리로 분리해보기?
    // 만약 새로운 게임을 하고싶다 가정하면,
    // Name 은 이미 있는 상태에서 Hands 만 바꿔보고 싶다.

    public Player(final Name name, final Hands hands) {
        super(name, hands);
        validate(name);
    }

    private void validate(final Name name) {
        if (DEALER_NAME.equals(name)) {
            throw new ReservedPlayerNameException(ErrorCode.RESERVED_NAME);
        }
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
