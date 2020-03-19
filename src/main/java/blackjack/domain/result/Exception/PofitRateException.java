package blackjack.domain.result.Exception;

public class PofitRateException extends RuntimeException {
    public PofitRateException() {
        super("주어진 조건에 해당하는 플레이어의 결과가 존재하지 않습니다.");
    }
}
