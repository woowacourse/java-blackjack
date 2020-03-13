package blackjack.domain.user;

import blackjack.domain.Outcome;

public class Player extends User {

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean canDrawCard() {
        return !(userCards.isBust() || userCards.isBlackJack());
    }

    public Outcome calculateOutcome(User dealer) {
        return userCards.calculateOutcome(dealer.userCards);
    }
}
