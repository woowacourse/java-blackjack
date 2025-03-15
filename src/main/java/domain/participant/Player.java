package domain.participant;

import domain.card.Hand;
import java.util.Objects;

public class Player extends Participant {
    private final String name;

    public Player(String name, Hand hand) {
        super(hand);
        this.name = name;
    }

    public boolean hasBustCards() {
        return hand.isBust();
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Player player = (Player) object;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
