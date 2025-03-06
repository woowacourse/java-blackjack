package blackjack;

import java.util.ArrayList;

public class Player extends Participant {

    private final String name;

    public Player(String name) {
        super(new ArrayList<>());
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
