package blackjack.domain.participant;

import blackjack.domain.Money;
import blackjack.domain.Name;
import blackjack.domain.Record;

public class Player extends Participant {

    private final Money money;
    private final Name name;

    public Player(final String name) {
        super();
        this.money = new Money();
        this.name = new Name(name);
    }

    public void initMoney(final int bettingAmount) {
        money.init(bettingAmount);
    }

    public void calculatePrize(final boolean isDealerBlackjack, final int dealerScore) {
        if (isBlackjack()) {
            updateWhenBlackjack(isDealerBlackjack);
        }

        if (Record.isPush(dealerScore, getScore())) {
            money.toZero();
        }
        if (Record.isLoss(dealerScore, getScore())) {
            money.toNegative();
        }
    }

    private void updateWhenBlackjack(final boolean isDealerBlackjack) {
        if (isDealerBlackjack) {
            money.toZero();
            return;
        }

        money.multiply();
    }

    public int getPrize() {
        return money.getValue();
    }

    @Override
    public String getName() {
        return name.getValue();
    }
}
