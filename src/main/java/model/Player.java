package model;

import static constant.GameRule.DEALER_NAME;

import constant.ErrorMessage;
import java.util.Objects;

public class Player extends Participant {

    public Player(PlayerName name) {
        super(name);
        validate(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player other = (Player) o;
        return this.getResult().name().equals(other.getResult().name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getResult().name());
    }

    private void validate(PlayerName name) {
        if(name.getName().equals(DEALER_NAME)) {
            throw new IllegalArgumentException(ErrorMessage.NO_PLAYER_NAME_DEALER.getMessage());
        }
    }

}
