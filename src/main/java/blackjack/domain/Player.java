package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;

public class Player extends Gamer {

    private static final int PUSH_PROFIT = 0;
    private static final double BLACKJACK_MULTIPLIER = 1.5;

    private final Name name;
    private final Money bettingMoney;

    private Player(final Name name, final Hand hand, final Money bettingMoney) {
        super(hand);
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public static Player from(final String name) {
        return new Player(new Name(name), new Hand(), new Money(0));
    }

    public static Player of(final String name, final double bettingMoney) {
        return new Player(new Name(name), new Hand(), new Money(bettingMoney));
    }

    public void draw(final Card card) {
        hand.add(card);
    }

    @Override
    public boolean canDraw() {
        return hand.calculateOptimalSum() <= BLACKJACK;
    }

    public Money calculateLoseProfit() {
        return bettingMoney.toNegative();
    }

    public Money calculateWinProfit() {
        return bettingMoney;
    }

    public Money calculatePushProfit() {
        return new Money(PUSH_PROFIT);
    }

    public Money calculateBlackjackWinProfit() {
        return bettingMoney.multiply(BLACKJACK_MULTIPLIER);
    }

    public Name getName() {
        return name;
    }
}
