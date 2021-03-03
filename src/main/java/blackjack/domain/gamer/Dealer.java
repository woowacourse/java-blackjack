package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;

import java.util.List;

public class Dealer extends Gamer {

    public Dealer(Hands hands) {
        this("딜러", hands);
    }

    private Dealer(String name, Hands hands) {
        super(name, hands);
    }

    @Override
    public List<Card> showOpenHands() {
        return hands.getCardOf(1);
    }
}
