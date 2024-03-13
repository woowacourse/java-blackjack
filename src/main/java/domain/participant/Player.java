package domain.participant;

import static domain.participant.Hands.BLACK_JACK;

public class Player extends Participant {

    public Player(final Name name, final Hands hands) {
        super(name, hands);
        validate(name);
    }

    @Override
    public boolean canDeal() {
        return handsSum() < BLACK_JACK;
    }

    private void validate(final Name name) {
        if (Dealer.NAME.equals(name.getValue())) {
            throw new IllegalArgumentException("[ERROR] 사용할 수 없는 이름입니다.");
        }
    }
}
