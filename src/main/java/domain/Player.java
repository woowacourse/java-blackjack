package domain;

import java.util.List;

public class Player extends Gamer {
    private final Name name;

    public Player(Name name) {
        this.name = name;
    }

    Player(Name name, List<Card> cards) {
        this.name = name;
        cards.forEach(hand::add);
    }

    public boolean isNameOf(String test) {
        return name.name().equals(test);
    }

    public String getName() {
        return name.name();
    }
}
