package blackjack.domain.gambler;

import blackjack.domain.card.Card;
import java.util.List;

public class Player extends Gambler {
    public Player(final Name name) {
        super(name);
    }

    @Override
    public boolean isPlayer() {
        return true;
    }

    @Override
    public List<Card> getInitialCards() {
        return getCards();
    }
}
