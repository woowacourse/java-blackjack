package blackjack.domain.participants;

import blackjack.domain.Result;
import blackjack.domain.card.Card;
import java.util.Collections;
import java.util.List;

public class Dealer extends Participant {

    private static final int MAX_SUM_FOR_MORE_CARD = 16;

    public Dealer(final Name name, final Money money) {
        super(name, money);
    }

    public Dealer(final Name name, final double money) {
        super(name, new Money(money));
    }

    public Dealer(final String name, final double money) {
        super(new Name(name), new Money(money));
    }

    public Dealer() {
        super("딜러");
    }

    public Dealer(final double money) {
        super(new Name("딜러"), new Money(money));
    }

    public Dealer(final String name) {
        super(name);
    }

    public Dealer(final Name name) {
        super(name);
    }

    @Override
    public Result decideWinner(Participant player) {
        if (this.calculate() == player.calculate()) {
            return Result.DRAW;
        }
        if (player.isBust() || (player.calculate() < this.calculate() && !this.isBust())) {
            return Result.WIN;
        }
        return Result.LOSE;
    }

    @Override
    public List<Card> initialCards() {
        return Collections.singletonList(getPlayerCards().get(0));
    }

    @Override
    public boolean checkMoreCardAvailable() {
        return (calculate() <= MAX_SUM_FOR_MORE_CARD);
    }

}
