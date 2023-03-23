package domain.user.state;

import static domain.game.Winning.LOSE;
import static domain.game.Winning.PUSH;
import static domain.game.Winning.WIN;

import domain.game.Winning;
import domain.user.Cards;

public class Stay extends Terminated {

    protected Stay(Cards cards) {
        super(cards);
    }

    @Override
    public Winning match(State dealer) {
        if (dealer.isBlackJack()) {
            return LOSE;
        }
        if (dealer.isBust()) {
            return WIN;
        }
        if (dealer.getScore() == getScore()) {
            return PUSH;
        }
        if (dealer.getScore() > getScore()) {
            return LOSE;
        }
        return WIN;
    }

    @Override
    public boolean isBlackJack() {
        return false;
    }

    @Override
    public boolean isBust() {
        return false;
    }
}
