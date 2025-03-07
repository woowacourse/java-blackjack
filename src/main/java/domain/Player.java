package domain;

import java.util.Objects;

public class Player extends Participant{
    private final String name;

    public Player(String name, Cards cards) {
        super(cards);
        this.name = name;
    }

    public boolean hasBustCards() {
        return cards.isBust();
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
