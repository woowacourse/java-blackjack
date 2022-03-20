package blackjack.domain.user;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.user.state.Ready;

public final class Dealer extends User {

    private static final String NAME = "딜러";
    private static final int INIT_COUNT = 1;

    public Dealer() {
        super(NAME, new Ready());
    }

    @Override
    public List<Card> showInitCards() {
        return this.state.getInitHandCards(INIT_COUNT);
    }
}
