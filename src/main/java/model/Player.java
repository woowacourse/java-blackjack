package model;

import constant.ErrorMessage;
import java.util.Objects;
import model.dto.PlayerName;

public class Player extends Participant {

    public Player(PlayerName name) {
        super(name);
        validate(name);
    }

    private void validate(PlayerName name) {
        if(name.value().equals("딜러")) {
            throw new IllegalArgumentException(ErrorMessage.NO_PLAYER_NAME_DEALER.getMessage());
        }
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

}
