package participant;

import card.Hand;
import result.GameStatus;
import java.util.Objects;

public class Player extends Participant {
    private final String name;

    public Player(String name, Hand hand) {
        super(hand);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public Hand openInitialHand() {
        return hand;
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
