package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;

import java.util.List;

public class Player extends Gamer {
    public Player(String name, Hands hands) {
        super(name, hands);
    }

    @Override
    public List<Card> showInitialHands() {
        return hands.getCardOf(2);
    }
}
