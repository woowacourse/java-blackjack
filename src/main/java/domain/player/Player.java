package domain.player;

import java.util.Objects;

public class Player extends Participant {
    private static final int HIT_UPPER_BOUND = 21;

    private final Name name;

    public Player(final Name name) {
        super();
        this.name = name;
    }

    public PlayerResult obtainResultBy(final Dealer dealer) {
        return PlayerResult.reverse(dealer.compareHandsWith(this));
    }

    public String getName() {
        return name.getValue();
    }

    @Override
    public boolean canHit() {
        return calculateScore() < HIT_UPPER_BOUND;
    }

    @Override
    public boolean canNotHit() {
        return !canHit();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
