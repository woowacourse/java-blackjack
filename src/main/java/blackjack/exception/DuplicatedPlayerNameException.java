package blackjack.exception;

public class DuplicatedPlayerNameException extends CustomException {

    private static final String MESSAGE = "중복된 이름입니다.";

    public DuplicatedPlayerNameException() {
        super(MESSAGE);
    }
}
