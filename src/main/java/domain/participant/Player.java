package domain.participant;

import domain.card.Hand;
import java.util.Objects;

public class Player extends Participant {
    private final String name;

    private Player(String name, Hand hand) {
        super(hand);
        this.name = name;
    }

    public static Player init(String name) {
        return new Player(name, Hand.empty());
    }

    public static Player from(String name, Hand hand) {
        return new Player(name, hand);
    }

    public String getName() {
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
