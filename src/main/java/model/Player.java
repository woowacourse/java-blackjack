package model;

import constant.ErrorMessage;
import java.util.Objects;

public class Player extends Participant {
    private static final String DEALER_NAME = "딜러";

    public Player(PlayerName name) {
        super(name);
        validate(name);
    }

    private void validate(PlayerName name) {
        if(name.value().equals(DEALER_NAME)) {
            ErrorMessage.NO_PLAYER_NAME_DEALER.throwException();
        }
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
