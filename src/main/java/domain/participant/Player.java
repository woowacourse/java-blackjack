package domain.participant;

import domain.card.Name;

public class Player extends Participant {

    private final Name name;

    public Player(Name name) {
        this.name = name;
    }

    public Player(String name) {
        this.name = new Name(name);
    }

    public Name getName() {
        return name;
    }

    public String getNameString() {
        return name.value();
    }
}
