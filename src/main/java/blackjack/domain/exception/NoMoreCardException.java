package blackjack.domain.exception;

public class NoMoreCardException extends CustomException {

    public NoMoreCardException() {
        super("뽑을 수 있는 카드가 없습니다.");
    }
}
