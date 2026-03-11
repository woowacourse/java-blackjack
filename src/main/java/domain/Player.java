package domain;

import java.util.ArrayList;
import java.util.List;

public class Player extends Participant {
    private static final int BUST_THRESHOLD = 21;

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean canHit() {
        return calculateScore() <= BUST_THRESHOLD;
    }
}
