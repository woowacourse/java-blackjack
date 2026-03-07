package blackjack.model;

import java.util.List;

public class Player extends Participant {

    public Player(String name, Hand hand) {
        super(name, hand);
    }

    @Override
    public List<Card> getVisibleCards() {
        return getCards();
    }
}
