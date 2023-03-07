package blackjack.view.exception;

import blackjack.domain.exception.CustomException;

public class MessageDoesNotExistException extends CustomException {

    public MessageDoesNotExistException() {
        super("해당하는 메세지가 없습니다");
    }
}
