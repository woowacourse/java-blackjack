package model.participant;

import java.util.Objects;

public class Player extends Participant {
    public Player(String name) {
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
}
