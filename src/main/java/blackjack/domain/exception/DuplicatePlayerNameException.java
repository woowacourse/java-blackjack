package blackjack.domain.exception;

public class DuplicatePlayerNameException extends CustomException {

    public DuplicatePlayerNameException() {
        super("중복인 이름은 입력하실 수 없습니다.");
    }
}
