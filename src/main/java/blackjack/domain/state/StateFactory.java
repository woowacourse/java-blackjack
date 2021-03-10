package blackjack.domain.state;

import blackjack.domain.user.Cards;

public class StateFactory {
    public static State generateStateByCards(Cards cards) {
        return new Hit(cards);
    }
}
