package blackjack.domain;

import java.util.Objects;

public class Player extends Person{

    private String name;

    private Player() {

    }

    public Player(String name) {
        this.name = name;
    }

    public static Player copy(Player original) {
        Player copy = new Player();
        copy.name = original.name;
        copy.myCards = original.getMyCards();
        return copy;
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
        return name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }
}
