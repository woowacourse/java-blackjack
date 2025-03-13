package blackjack.object.gambler;

import blackjack.object.card.Card;
import java.util.List;

public class Player extends Gambler {
    public Player(final Name name) {
        super(name);
    }

    @Override
    public List<Card> getInitialCards() {
        return getCards();
    }
}
