package blackjack.domain.participant;

import java.util.Objects;

public class Player extends Participant {

    private static final Score BLACKJACK_SCORE = new Score(21);

    private final Name name;

    public Player(Name name) {
        super();
        this.name = name;
    }

    @Override
    public boolean canReceive() {
        return hand.calculateScore().isLessThan(BLACKJACK_SCORE);
    }

    public String getName() {
        return name.getName();
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
