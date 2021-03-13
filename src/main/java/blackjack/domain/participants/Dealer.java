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
        if (this.isBlackjack() && !player.isBlackjack()) {
            return Result.BLACKJACK;
        }
        if (player.isBust()) {
            return Result.WIN;
        }
        if (this.isBust()) {
            return Result.LOSE;
        }
        return decideWinnerWithScores(player);
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
