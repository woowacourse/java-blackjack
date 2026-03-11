package blackjack.domain;

import blackjack.utils.Formatter;

public class Bet {

    private static final int BET_AMOUNT_UNIT = 1000;
    private static final int BET_MINIMUM_AMOUNT = 5000;
    private static final int BET_MAXIMUM_AMOUNT = 100_000_000;

    private final int amount;

    public Bet(final int amount) {
        validateAmountUnit(amount);
        validateAmountSize(amount);
        this.amount = amount;
    }

    private void validateAmountUnit(final int amount) {
        if (amount % BET_AMOUNT_UNIT != 0) {
            throw new IllegalArgumentException(String.format(
                "베팅금은 %s 단위여야 합니다.", Formatter.amountFormat(BET_AMOUNT_UNIT)));
        }
    }

    private void validateAmountSize(final int amount) {
        if (amount < BET_MINIMUM_AMOUNT) {
            throw new IllegalArgumentException(String.format(
                "베팅금은 %s 이상이어야 합니다.", Formatter.amountFormat(BET_MINIMUM_AMOUNT)));
        }
        if (amount > BET_MAXIMUM_AMOUNT) {
            throw new IllegalArgumentException(String.format(
                "베팅금은 %s 이하여야 합니다.", Formatter.amountFormat(BET_MAXIMUM_AMOUNT)));
        }
    }
}
