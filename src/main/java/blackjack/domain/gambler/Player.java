package blackjack.domain.gambler;

import java.util.Objects;

public class Player extends Gambler {
    private final Name name;

    public Player(final Name name) {
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    public boolean isNameEquals(Name playerName) {
        return Objects.equals(playerName, name);
    }
}
