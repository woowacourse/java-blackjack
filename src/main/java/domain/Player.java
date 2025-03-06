package domain;

import java.util.Objects;

public class Player extends Participant {

    private final String name;

    private Player(String name, Cards cards) {
        super(cards);
        this.name = name;
    }

    public static Player init(String name) {
        return new Player(name, Cards.empty());
    }

    public static Player from(String name, Cards cards) {
        return new Player(name, cards);
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
