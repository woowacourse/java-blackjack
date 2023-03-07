package blackjack.controller.exception;

import blackjack.domain.exception.CustomException;

public class NotCommandException extends CustomException {

    public NotCommandException() {
        super("y 또는 n를 입력해주세요.");
    }
}
