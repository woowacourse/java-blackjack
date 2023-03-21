package exception;

public class DealerHasNoProfitRatioException extends RuntimeException {

    public static final String MESSAGE = "[ERROR] 딜러의 최종 수익은 자동으로 계산됩니다.";

    public DealerHasNoProfitRatioException() {
        super(String.format(MESSAGE));
    }
}
