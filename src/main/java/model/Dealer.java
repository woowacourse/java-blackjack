package model;

import java.util.List;

public class Dealer extends Participator {
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(new PlayerName(DEALER_NAME));
    }

    @Override
    public List<Card> getCards() {
        return List.of(super.getCards().get(1));
    }
}
