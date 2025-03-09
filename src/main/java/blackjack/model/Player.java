package blackjack.model;

import java.util.ArrayList;
import java.util.Objects;

public final class Player extends Participant {

    private final Name name;

    public Player(Name name) {
        super(new ArrayList<>());
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
