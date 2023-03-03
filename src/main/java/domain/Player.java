package domain;

public class Player extends Participant {

    public Player(String name) {
        super(new Name(name));
    }
}
