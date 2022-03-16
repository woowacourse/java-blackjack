package blackjack.domain.participant;

public class BettingAmount {

    private static final int MIN_AMOUNT = 1000;
    private static final String ERROR_MONEATRY_UNIT = String.format("배팅 금액의 단위는 %d원 입니다.", MIN_AMOUNT);

    private final long value;

    public BettingAmount(long value) {
        this.value = value;
        validation(this.value);
    }

    private void validation(long value) {
        if (value % MIN_AMOUNT != 0) {
            throw new IllegalArgumentException(ERROR_MONEATRY_UNIT);
        }
    }

}
