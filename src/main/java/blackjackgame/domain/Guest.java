package blackjackgame.domain;

import java.util.ArrayList;
import java.util.Objects;

public class Guest extends Player {
    Name name;

    public Guest(Name name) {
        this.name = name;
        cards = new ArrayList<>();
    }

    public boolean isPick() {
        return getScore() < 21;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Guest guest = (Guest)o;
        return Objects.equals(name, guest.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
