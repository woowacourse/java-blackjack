package blackjack.domain.user;

import blackjack.domain.card.BettingAmount;

import java.util.Objects;

public class Player extends User {
    private BettingAmount bettingAmount;

    public Player(String name) {
        super(name);
    }

    public Player(Name name, BettingAmount bettingAmount) {
        super(name);

        Objects.requireNonNull(bettingAmount);
        bettingAmount = bettingAmount;
    }
    @Override
    public boolean receivable() {
        return !(new Point(getCards()).isBust());
    }

}
