package blackjack.domain;

public class Player extends User {
    public Player(String name) {
        super(new Name(name));
    }
}
