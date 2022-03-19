package blackjack.domain.user;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.user.state.Ready;

public class Dealer extends User {

    private static final int INIT_COUNT = 1;

    public Dealer() {
        super("딜러", new Ready());
    }

    @Override
    public List<Card> showInitCards() {
        return this.state.getInitHandCards(INIT_COUNT);
    }
}
