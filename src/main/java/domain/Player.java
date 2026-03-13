package domain;

import java.util.List;

public class Player extends Participant {

    public Player(String name) {
        super(name);
    }

    @Override
    public List<Card> getInitialVisibleCards() {
        return super.getCards();
    }

    @Override
    public boolean isDrawable() {
        return super.isLessThanMaxScore();
    }
}
