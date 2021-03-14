package blakcjack.domain.money;

public class IllegalEarningRateException extends IllegalArgumentException {
    public IllegalEarningRateException() {
        super("해당하는 EarningRate를 찾을 수 없습니다.");
    }
}
