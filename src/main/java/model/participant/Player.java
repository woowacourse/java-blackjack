package model.participant;

import dto.status.PlayerName;
import dto.status.PlayerStatus;
import java.util.Objects;

public class Player extends Participant {
    public Player(PlayerName name) {
        super(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player other = (Player) o;
        return super.getName().equals(other.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getName());
    }

    public PlayerStatus getPlayerStatus(Long bet) {
        return new PlayerStatus(super.getName(), super.getScore(), bet, super.isBust(), super.isBlackJack());
    }
}
