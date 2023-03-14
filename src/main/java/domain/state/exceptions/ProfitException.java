package domain.state.exceptions;

public class ProfitException extends RuntimeException {

    private static final String MESSAGE = "수익을 계산할 수 없는 상태입니다.";

    public ProfitException() {
        super(MESSAGE);
    }
}
