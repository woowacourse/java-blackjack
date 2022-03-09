package blackjack.domain;

public class Player extends Gamer {

    public Player(Name name) {
        super(name);
    }

    public static Gamer from(Name name) {
        return new Player(name);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
