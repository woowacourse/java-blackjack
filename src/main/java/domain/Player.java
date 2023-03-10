package domain;

import java.util.ArrayList;
import java.util.List;

public class Player extends User {
    private static final int PLAYER_HIT_LIMIT = 21;

    private final String name;

    public Player(String name) {
        this(name, new ArrayList<>());
    }

    public Player(String name, List<Card> cards) {
        super(new Hand(cards));
        this.name = name;
    }

    public boolean canHit() {
        return score().isLessThan(PLAYER_HIT_LIMIT);
    }

    @Override
    public String getName() {
        return name;
    }
}


