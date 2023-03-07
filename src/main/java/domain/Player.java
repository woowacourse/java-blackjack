package domain;

import java.util.Objects;

public class Player extends Participant {

    private static final int UPPER_BOUND_OF_DRAWABLE_SCORE = 21;
    private final Name name;
    private Decision decision;

    public Player(String inputName) {
        this.name = new Name(inputName);
        this.decision = Decision.HIT;
    }

    public void stand() {
        decision = Decision.STAND;
    }

    @Override
    public boolean isDrawable() {
        return decision == Decision.HIT &&
                score() < UPPER_BOUND_OF_DRAWABLE_SCORE;
    }

    @Override
    public String name() {
        return name.value();
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
