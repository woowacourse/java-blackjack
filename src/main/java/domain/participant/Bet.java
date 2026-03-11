package domain.participant;

import exception.OutOfRangeException;

public record Bet(long amount) {
    private static final int MIN_BETTING_AMOUNT = 1;
    private static final long MAX_BETTING_AMOUNT = Long.MAX_VALUE;

    public Bet {
        validateAmount(amount);
    }

    private void validateAmount(long amount) {
        if (amount < MIN_BETTING_AMOUNT) {
            throw new OutOfRangeException(String.format("베팅 금액은 최소 %d원은 넘어야 합니다.", MIN_BETTING_AMOUNT));
        }

        if (amount == MAX_BETTING_AMOUNT) {
            throw new OutOfRangeException(String.format("베팅 금액은 %d원을 넘을 수 없습니다.", MAX_BETTING_AMOUNT));
        }
    }
}
