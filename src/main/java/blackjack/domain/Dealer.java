package blackjack.domain;

import java.util.Collections;
import java.util.List;

public class Dealer extends User {

    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    public List<String> getFirstCard() {
        return List.of(cards.getFirst().getName());
    }
}
