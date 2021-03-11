package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import java.util.List;

public class Dealer extends Gamer {

    private static final int OPEN_HAND_COUNT = 1;

    public Dealer(Hands hands) {
        this("딜러", hands);
    }

    private Dealer(String name, Hands hands) {
        super(name, hands);
    }

    public boolean checkBoundary() {
        return hands.calculate().dealerAbleToAdd();
    }

    @Override
    public List<Card> showOpenHands() {
        return hands.cardsOf(OPEN_HAND_COUNT);
    }
}
