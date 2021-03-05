package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import java.util.List;

public class Player extends Gamer {

    public Player(String name) {
        super(name);
    }

    @Override
    public List<Card> showOpenHands() {
        return hands.getCardOf(2);
    }
}
