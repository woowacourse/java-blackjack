package model;

import java.util.Objects;

public class Player {

    public static final Player DEADLER = new Player("딜러");

    private final String name;

    public Player(final String name) {
        this.name = name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return this.name;
    }
}
