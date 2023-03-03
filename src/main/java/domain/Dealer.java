package domain;

import domain.user.Participant;
import java.util.List;

public class Dealer extends Participant {

    public static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public List<Card> getReadyCards() {
        if (cards.size() < 1) {
            throw new IllegalStateException();
        }
        return List.of(cards.get(0));
    }
}
