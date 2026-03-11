package domain.participant;

import domain.card.Card;
import domain.card.CardsSnapshot;
import java.util.List;

public class Dealer extends Participant {
    private final static String DEFAULT_NAME = "딜러";

    public Dealer() {
        super(new Name(DEFAULT_NAME));
    }

    public CardsSnapshot startingHandInfo() {
        List<Card> card = List.of(handInfo().cards().getFirst());
        return new CardsSnapshot(card);
    }

    @Override
    public boolean canReceive() {
        return getScore() <= 16;
    }
}
