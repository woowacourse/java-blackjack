package blackjack.domain.participant;

import static blackjack.constant.CommonConstant.BLACKJACK_SYMBOL_NUMBER;

public class Player extends Participant {

    public Player(String name) {
        super(new Name(name));
    }

    public boolean canReceive() {
        return getScore() < BLACKJACK_SYMBOL_NUMBER;
    }
}
