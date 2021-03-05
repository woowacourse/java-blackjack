package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import java.util.List;

public class Dealer extends Gamer {

    private static final String FIXED_DEALER_NAME = "딜러";
    private static final int ADD_CARD_BOUNDARY = 17;

    private Dealer(String name) {
        super(name);
    }

    public Dealer() {
        this(FIXED_DEALER_NAME);
    }

    public boolean checkBoundary() {
        return (hands.calculate() < ADD_CARD_BOUNDARY);
    }

    @Override
    public List<Card> showOpenHands() {
        return hands.getCardOf(1);
    }
}
