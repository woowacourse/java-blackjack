package model.betting;

import java.util.Objects;
import model.participant.Player;

public class Bet {
    private final int money;
    private final Player better;

    public Bet(int money, Player better) {
        this.money = money;
        this.better = better;
    }

    public int getMoney() {
        return money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bet bet = (Bet) o;
        return money == bet.money && Objects.equals(better, bet.better);
    }

    @Override
    public int hashCode() {
        return Objects.hash(money, better);
    }
}
