package domain.user;

import domain.deck.PlayerDeck;
import java.util.Objects;

public class Player extends User {
    private final Name name;

    public Player(Name name) {
        super(new PlayerDeck());
        this.name = name;
    }

    @Override
    public boolean isPlayer() {
        return true;
    }

    @Override
    public String getNameValue() {
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
