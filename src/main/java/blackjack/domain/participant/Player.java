package blackjack.domain.participant;

import static blackjack.domain.card.Cards.MAX_SCORE;

import blackjack.domain.Money;
import blackjack.domain.Name;

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

        if (isPush(dealerScore)) {
            money.toZero();
        }
        if (isLoss(dealerScore)) {
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

    private boolean isPush(final int dealerScore) {
        return dealerScore == getScore() && getScore() <= MAX_SCORE;
    }

    private boolean isLoss(final int dealerScore) {
        if (getScore() > MAX_SCORE) {
            return true;
        }
        if (dealerScore > MAX_SCORE) {
            return false;
        }
        return dealerScore > getScore();
    }


    public int getPrize() {
        return money.getValue();
    }

    @Override
    public String getName() {
        return name.getValue();
    }
}
