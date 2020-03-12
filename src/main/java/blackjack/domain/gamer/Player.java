package blackjack.domain.gamer;

import java.util.Objects;

public class Player extends Gamer {

    private final String name;

    public Player(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public String getName() {
        return name;
    }
}