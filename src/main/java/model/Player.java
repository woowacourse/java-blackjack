package model;

import exception.GameException;
import java.util.Objects;

public class Player extends Participant {
    private static final String DEALER_NAME = "딜러";

    private final BettingMoney bettingMoney;

    public Player(PlayerName name, BettingMoney bettingMoney) {
        super(name);
        validate(name);
        this.bettingMoney = bettingMoney;
    }

    private void validate(PlayerName name) {
        if (name.value().equals(DEALER_NAME)) {
            throw new GameException("플레이어는 '딜러'라는 이름을 가질 수 없습니다.");
        }
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player other = (Player) o;
        return this.getName().equals(other.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName());
    }

}
