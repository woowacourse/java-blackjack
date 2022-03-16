package blackjack.domain.participant;

import blackjack.domain.Money;
import blackjack.domain.Record;

public class Player extends Participant {

    private final Money money;

    public Player(final String name) {
        super(name);
        this.money = new Money();
    }

    public void initMoney(final int bettingAmount) {
        money.init(bettingAmount);
    }

    public void calculatePrize(final boolean isDealerBlackjack, final int dealerScore) {
        if (isDealerBlackjack && isBlackjack()) {
            money.toZero();
            return;
        }

        if (isBlackjack()) {
            money.multiply();
            return;
        }

        final Record playerRecord = Record.of(dealerScore, getScore());
        if (playerRecord == Record.PUSH) {
            money.toZero();
        }
        if (playerRecord == Record.LOSS) {
            money.toNegative();
        }
    }

    public int getPrize() {
        return money.getValue();
    }
}
