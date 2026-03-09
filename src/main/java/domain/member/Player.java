package domain.member;

import domain.card.Card;
import java.util.List;

public class Player extends Member {

    public Player(String name) {
        super(name);
    }

    @Override
    public List<Card> showFirstCards() {
        return super.handCards();
    }
}
