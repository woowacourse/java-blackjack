package blackjack.domain;

import java.util.Objects;

public class Player extends Gambler {
    private final String name;

    public Player(final String name) {
        validateName(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private String validateName(String name) {
        if (name.length() < 2 || name.length() > 5) {
            throw new IllegalArgumentException("[ERROR]");
        }
        if (name.split(" ").length != 1) {
            throw new IllegalArgumentException("[ERROR]");
        }
        return name;
    }

    public boolean isNameEquals(String playerName) {
        return Objects.equals(playerName, name);
    }
}
