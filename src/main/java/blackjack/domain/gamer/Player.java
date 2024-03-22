package blackjack.domain.gamer;

import blackjack.domain.betting.BettingMoney;
import blackjack.domain.card.Cards;
import blackjack.domain.result.GameResult;

public final class Player extends Gamer {
    private final Name name;
    private final BettingMoney bettingMoney;

    public Player(final String name, final int bettingMoney) {
        this(new Name(name), new BettingMoney(bettingMoney));
    }

    public Player(final Name name, final BettingMoney bettingMoney) {
        this(name, bettingMoney, Cards.createEmpty());
    }

    public Player(final Name name, final BettingMoney bettingMoney, final Cards cards) {
        super(cards);
        this.bettingMoney = bettingMoney;
        this.name = name;
    }

    public int calculateProfit(final GameResult gameResult) {
        return bettingMoney.calculateProfit(gameResult);
    }

    public String getName() {
        return name.value();
    }

    @Override
    public String toString() {
        return "Player{" +
                "name=" + name +
                ", cards=" + cards +
                '}';
    }
}
