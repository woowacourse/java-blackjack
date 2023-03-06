package domain;

import java.util.ArrayList;
import java.util.List;


public class Dealer extends User {

    public Dealer() {
        this(new ArrayList<>());
    }

    public Dealer(List<Card> cards) {
        super(cards);
    }

    @Override
    public boolean canHit() {
        return getScore() <= 16;
    }

    @Override
    public String getName() {
        return "딜러";
    }

}


