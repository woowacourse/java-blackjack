package model;

import java.util.List;

public class Dealer extends Participator {
    public Dealer() {
        super();
    }

    @Override
    public List<Card> getCards() {
        return List.of(super.getCards().get(1));
    }
}
