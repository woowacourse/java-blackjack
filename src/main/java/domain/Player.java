package domain;

import java.util.ArrayList;
import java.util.List;

public class Player extends User {
    private final String name;

    public Player(String name) {
        this(name, new ArrayList<>());
    }

    public Player(String name, List<Card> cards) {
        super(new Hand(cards));
        this.name = name;
    }

    @Override
    public boolean canHit() {
        return score().getValue() < 21;
    }

    @Override
    public String getName() {
        return name;
    }
}


