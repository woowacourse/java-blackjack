package blackjack.exception;

public class PlayerNotFoundException extends RuntimeException {

    private static final String MESSAGE = "플레이어를 찾을 수 없습니다.";

    public PlayerNotFoundException() {
        super(MESSAGE);
    }
}
