package blackjack.domain.participant;

import java.util.Objects;

public class BettingPlayer extends Player {
    private final Money money;

    public BettingPlayer(Name name, Money money) {
        super(name);
        Objects.requireNonNull(money);
        this.money = money;
    }
}
