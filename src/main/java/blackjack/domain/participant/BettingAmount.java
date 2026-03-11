package blackjack.domain.participant;

import blackjack.exception.ExceptionMessage;

public class BettingAmount {

    private static final int MIN_AMOUNT = 5_000;
    private static final int MAX_AMOUNT = 200_000;
    private static final int BETTING_UNIT = 1_000;

    private final int bettingAmount;

    public BettingAmount(int bettingAmount) {
        validate(bettingAmount);
        this.bettingAmount = bettingAmount;
    }

    private void validate(int bettingAmount) {
        validateRange(bettingAmount);
        validateUnit(bettingAmount);
    }

    private void validateRange(int bettingAmount) {
        if (isOutOfRange(bettingAmount)) {
            throw new IllegalArgumentException(ExceptionMessage.BETTING_AMOUNT_OUT_OF_RANGE.getMessage());
        }
    }

    private boolean isOutOfRange(int bettingAmount) {
        return bettingAmount < MIN_AMOUNT || bettingAmount > MAX_AMOUNT;
    }

    private void validateUnit(int bettingAmount) {
        if (isInvalidUnit(bettingAmount)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_BETTING_UNIT.getMessage(BETTING_UNIT));
        }
    }

    private boolean isInvalidUnit(int bettingAmount) {
        return bettingAmount % BETTING_UNIT != 0;
    }
    //참여자: 21을 초과할 경우 배팅 금액을 모두 잃게 된다 | 딜러x & 처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅 금액의 1.5 배를 딜러에게 받는다.
    //딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다.
    //
    // 딜러: 21을 초과하면 그 시점까지 남아 있던 플레이어들은 베팅 금액을 받는다.
}
