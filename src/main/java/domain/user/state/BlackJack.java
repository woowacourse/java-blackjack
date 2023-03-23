package domain.user.state;

import static domain.game.Winning.BLACKJACK;
import static domain.game.Winning.PUSH;

import domain.game.Winning;
import domain.user.Cards;

public class BlackJack extends Terminated {

    protected BlackJack(Cards cards) {
        super(cards);
    }

    @Override
    public Winning match(State dealer) {
        if (dealer.isBlackJack()) {
            return PUSH;
        }
        return BLACKJACK;
    }

    @Override
    public boolean isBlackJack() {
        return true;
    }

    @Override
    public boolean isBust() {
        return false;
    }
}
