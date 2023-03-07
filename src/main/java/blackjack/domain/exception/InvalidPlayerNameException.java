package blackjack.domain.exception;

public class InvalidPlayerNameException extends CustomException {

    public InvalidPlayerNameException() {
        super("이름은 공백일 수 없습니다.");
    }
}
