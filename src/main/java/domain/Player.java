package domain;

import java.util.Objects;

public class Player extends Participant {
    private static final int UPPER_BOUND_OF_DRAWABLE_SCORE = 21;

    private final Name name;
    private HitOrStand hitOrStand;

    public Player(String inputName) {
        this.name = new Name(inputName);
        this.hitOrStand = HitOrStand.HIT;
    }

    public void stand() {
        hitOrStand = HitOrStand.STAND;
    }

    @Override
    public boolean isDrawable() {
        return hitOrStand == HitOrStand.HIT &&
                hand.calculateScore() < UPPER_BOUND_OF_DRAWABLE_SCORE;
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
