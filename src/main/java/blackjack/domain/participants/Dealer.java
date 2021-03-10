package blackjack.domain.participants;

import blackjack.domain.Result;
import blackjack.domain.card.Card;
import java.util.Collections;
import java.util.List;

public class Dealer extends Participant {

    private static final int MAX_SUM_FOR_MORE_CARD = 16;

    public Dealer(final String name, final double money) {
        super(new Name(name), money);
    }

    public Dealer() {
        super("딜러");
    }

    public Dealer(final double money) {
        super(new Name("딜러"), money);
    }

    public Dealer(final String name) {
        super(name);
    }

    public Dealer(final Name name) {
        super(name);
    }

    @Override
    public Result decideWinner(final Participant player) {
        if ((this.isBlackjack() && player.isBlackjack()) || this.isSameScore(player)) {
            return Result.DRAW;
        }
        if (this.isBlackjack()) {
            return Result.BLACKJACK;
        }
        if (player.isBust() || (!this.isBust() && (this.calculate() > player.calculate()))) {
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
