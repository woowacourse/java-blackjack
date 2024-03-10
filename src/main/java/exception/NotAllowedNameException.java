package exception;

import domain.gamer.Player;

public class NotAllowedNameException extends RuntimeException {
    public static final String NOT_ALLOWED_NAME = String.format("%s는 사용할 수 없는 이름입니다.", Player.DEALER_NAME);

    public NotAllowedNameException(final String message) {
        super(message);
    }
}
