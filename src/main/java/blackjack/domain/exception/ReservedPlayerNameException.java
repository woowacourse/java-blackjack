package blackjack.domain.exception;

import java.text.MessageFormat;

public class ReservedPlayerNameException extends CustomException {

    public ReservedPlayerNameException(String message) {
        super(MessageFormat.format("이름은 {0}일 수 없습니다.", message));
    }
}
