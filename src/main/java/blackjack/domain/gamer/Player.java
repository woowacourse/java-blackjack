package blackjack.domain.gamer;

public class Player extends Person {

    public Player(String name) {
        super.name = name;
    }

    public boolean isEqualName(String name) {
        return this.name.equals(name);
    }
}
