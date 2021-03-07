package blakcjack.domain.blackjackgame;

public class GameInitializationFailureException extends RuntimeException {
    public GameInitializationFailureException() {
        super("게임 초기화에 실패했습니다.");
    }
}
