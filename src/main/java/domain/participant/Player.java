package domain.participant;

import java.util.Objects;

public class Player extends Participant {
    public Player(PlayerName name, Hand hand) {
        super(name, hand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player player)) return false;
        return Objects.equals(getName(), player.getName());
    }
}
