package blackjack.domain.participant;

import blackjack.domain.machine.Betting;
import java.util.Objects;

public class Player extends Participant {
    private final Betting betting;

    public Player(String name, long bettingMoney) {
        super(name);
        betting = new Betting(bettingMoney);
    }

    public Betting getBetting() {
        return betting;
    }
}
