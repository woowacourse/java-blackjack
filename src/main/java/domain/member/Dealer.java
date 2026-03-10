package domain.member;

import domain.card.Card;
import java.util.List;

public class Dealer extends Member {

    private static final String DEFAULT_NAME = "딜러";

    public Dealer() {
        super(DEFAULT_NAME);
    }

    @Override
    public List<Card> showFirstCards() {
        return List.of(super.handCards().getFirst());
    }

    @Override
    public boolean isDealer() {
        return true;
    }
}
