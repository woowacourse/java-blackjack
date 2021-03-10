package blackjack.domain.participants;

import blackjack.domain.Result;
import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class Player extends Participant {

    public Player(final Name name, final Money money) {
        super(name, money);
    }

    public Player(final Name name, final double money) {
        super(name, new Money(money));
    }

    public Player(final String name, final double money) {
        super(new Name(name), new Money(money));
    }

    public Player(final String name) {
        super(name);
    }

    public Player(final Name name) {
        super(name);
    }

    @Override
    public Result decideWinner(Participant participant) {
        if (this.calculate() == participant.calculate() || (this.isBust() && participant.isBust())) {
            return Result.DRAW;
        }
        if (this.isBust() || (!participant.isBust() && participant.calculate() > this.calculate())) {
            return Result.LOSE;
        }
        return Result.WIN;
    }

    @Override
    public List<Card> initialCards() {
        return new ArrayList<>(getPlayerCards());
    }

    @Override
    public boolean checkMoreCardAvailable() {
        return !isBust();
    }

}
