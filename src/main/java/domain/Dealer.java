package domain;

import java.util.ArrayList;
import java.util.List;

public class Dealer extends Player {

    private static final String DEFAULT_NAME = "딜러";

    private static final Score UNHITTABLE_MIN = new Score(17);

    public Dealer() {
        this(new ArrayList<>());
    }

    public Dealer(List<Card> cards) {
        super(DEFAULT_NAME, cards);
    }

    @Override
    public boolean canHit() {
        return getScore().isLessThan(UNHITTABLE_MIN);
    }
}


