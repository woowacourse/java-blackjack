package blackjack.domain.participant;

import java.util.Objects;

public class Player extends Participant {

    private final Name name;

    public Player(final Name name) {
        this.name = name;
    }

    public String getName() {
        return this.name.getValue();
    }

    public boolean equalsName(final Name name) {
        return this.name.equals(name);
    }

    @Override
    public boolean isHitable() {
        return !isBurst();
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
        return Objects.hash(name);
    }
}
