package blackjack.domain.user;

import blackjack.domain.Outcome;

public class Player extends User {

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean canDrawCard() {
        return !(cards.isBust() || cards.isBlackJack());
    }

    public Outcome calculateOutcome(User dealer) {
        return cards.calculateOutcome(dealer.cards);
    }
}
