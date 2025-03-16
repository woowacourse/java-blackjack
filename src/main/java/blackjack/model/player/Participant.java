package blackjack.model.player;

import blackjack.model.game.BettedMoney;

import java.util.Objects;

public class Participant extends Player {

    private final PlayerName name;
    private final BettedMoney bettedMoney;

    public Participant(final PlayerName name, final BettedMoney bettedMoney) {
        this.name = name;
        this.bettedMoney = bettedMoney;
    }

    public String getName() {
        return name.getName();
    }

    public long getBettedMoney() {
        return bettedMoney.getMoney();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
