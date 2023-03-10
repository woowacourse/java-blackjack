package domain;

import java.util.ArrayList;
import java.util.List;

public class User extends Player {

    private static final Score UNHITTABLE_MIN = new Score(21);

    public User(String name) {
        this(name, new ArrayList<>());
    }

    public User(String name, List<Card> cards) {
        super(name, cards);
    }

    @Override
    public boolean canHit() {
        return getScore().isLessThan(UNHITTABLE_MIN);
    }
}