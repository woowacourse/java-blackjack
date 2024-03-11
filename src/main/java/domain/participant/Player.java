package domain.participant;

import domain.Hands;

public class Player extends Participant {
    public Player(final Name name, final Hands hands) {
        super(name, hands);
        validate(name);
    }

    private void validate(final Name name) {
        if (Dealer.NAME.equals(name.getValue())) {
            throw new IllegalArgumentException("[ERROR] 사용할 수 없는 이름입니다.");
        }
    }
}
