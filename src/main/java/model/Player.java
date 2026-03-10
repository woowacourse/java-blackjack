package model;

import constant.PlayerErrorCode;
import exception.GameException;
import java.util.Objects;

public class Player extends Participant {
    private static final String DEALER_NAME = "딜러";

    private final BattingMoney battingMoney;

    public Player(PlayerName name, BattingMoney battingMoney) {
        super(name);
        validate(name);
        this.battingMoney = battingMoney;
    }

    private void validate(PlayerName name) {
        if(name.value().equals(DEALER_NAME)) {
            throw new GameException(PlayerErrorCode.NO_PLAYER_NAME_DEALER);
        }
    }

    public BattingMoney getBattingMoney() {
        return battingMoney;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player other = (Player) o;
        return this.getName().equals(other.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName());
    }

}
