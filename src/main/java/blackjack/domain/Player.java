package blackjack.domain;

import java.util.Objects;

public class Player {
    private final String name;

    private Player(String name) {
        this.name = name;
    }

    public static Player create(String name) {
        validateSpace(name);
        return new Player(name);
    }

    private static void validateSpace(String name) {
        if(name.contains(" ")) {
            throw new IllegalArgumentException("이름에 공백이 포함됩니다.");
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
