package blackjack.exception;

public class NoMoreCardException extends CustomException {

    private static final String MESSAGE = "더 이상 뽑을 카드가 없습니다. 결과 창으로 넘어갑니다.";

    public NoMoreCardException() {
        super(MESSAGE);
    }
}
