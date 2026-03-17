package domain.participant;

public record BetAmount(
        int betAmount
) {
    public static final int MINIMUM_BET_AMOUNT = 0;
    public static final int MAXIMUM_BET_AMOUNT = 1_000_000;

    private static final int BET_UNIT = 10;
    private static final String BET_AMOUNT_OUT_OF_RANGE =
            "베팅 금액은 " + MINIMUM_BET_AMOUNT + "원 이상 " + MAXIMUM_BET_AMOUNT + "원 이하여야 합니다.";
    private static final String INVALID_BET_UNIT = "베팅 금액은 " + BET_UNIT + "단위여야 합니다.";

    public BetAmount {
        validateBetAmountRange(betAmount);
        validateBetUnit(betAmount);
    }


    // TODO: 베팅 금액 검증 테스트 추가
    private void validateBetAmountRange(final int betAmount) {
        if (betAmount < MINIMUM_BET_AMOUNT || betAmount > MAXIMUM_BET_AMOUNT) {
            throw new IllegalArgumentException(BET_AMOUNT_OUT_OF_RANGE);
        }
    }

    private void validateBetUnit(final int betAmount) {
        if (betAmount % BET_UNIT != 0) {
            throw new IllegalArgumentException(INVALID_BET_UNIT);
        }
    }
}
