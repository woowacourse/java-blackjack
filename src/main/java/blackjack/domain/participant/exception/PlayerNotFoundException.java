package blackjack.domain.participant.exception;

public class PlayerNotFoundException extends RuntimeException {

    public PlayerNotFoundException() {
        super("없는 사용자 입니다");
    }
}
