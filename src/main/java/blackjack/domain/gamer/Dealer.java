package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import java.util.List;

public class Dealer extends Gamer {

    private static final int ADD_CARD_BOUNDARY = 17;

    public Dealer(Hands hands) {
        this("딜러", hands);
    }

    private Dealer(String name, Hands hands) {
        super(name, hands);
    }

    public boolean checkBoundary() {
        return (hands.calculate() < ADD_CARD_BOUNDARY);
    }

    @Override
    public List<Card> showOpenHands() {
        return hands.cardsOf(1);
    }
}
