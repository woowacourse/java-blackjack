package domain.participant;

public class Player extends Participant {
    public Player(String name) {
        super(name);
    }

    public String getName() {
        return name.toString();
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
