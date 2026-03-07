package domain.participant;

public class Player extends Participant {
    public Player(String name) {
        super(name);
    }

    public Name getName() {
        return name;
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
