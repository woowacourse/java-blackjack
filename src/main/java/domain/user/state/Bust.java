package domain.user.state;

import static domain.game.Winning.LOSE;

import domain.game.Winning;
import domain.user.Cards;

public class Bust extends Terminated {

    protected Bust(Cards cards) {
        super(cards);
    }

    @Override
    public Winning match(State dealer) {
        return LOSE;
    }

    @Override
    public boolean isBlackJack() {
        return false;
    }

    @Override
    public boolean isBust() {
        return true;
    }
}
