package blackjack.domain.participant;

import java.util.Objects;

public class Player extends Participant {

    private final Name name;

    public Player(Name name) {
        super();
        this.name = name;
    }

    @Override
    public boolean canReceive() {
        return calculateCurrentScore() < BLACK_JACK_SCORE;
    }

    public Name getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
