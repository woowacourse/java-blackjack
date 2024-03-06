package blackjack.domain.player;

import blackjack.domain.HandGenerator;
import blackjack.domain.Name;

public class Dealer extends Participant {
    private static final String DEALER_NAME = "딜러";

    public Dealer(HandGenerator handGenerator) {
        super(new Name(DEALER_NAME), handGenerator);
    }
}
