package domain;

import java.util.List;

public class Player extends Participant {

    public Player(String name) {
        super(name);
    }

    @Override
    public List<Card> getShownCard() {
        return getCards();
    }
}
