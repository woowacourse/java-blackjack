package domain;

import java.util.ArrayList;
import java.util.List;

public class Player extends User {

    public Player(String name) {
        this(name, new ArrayList<>());
    }

    public Player(String name, List<Card> cards) {
        super(name, cards);
    }

    public boolean canHit() {
        return getScore() < 21;
    }

}


