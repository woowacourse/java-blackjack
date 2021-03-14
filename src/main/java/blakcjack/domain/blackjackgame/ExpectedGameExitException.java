package blakcjack.domain.blackjackgame;

public class ExpectedGameExitException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "게임 초기화에 실패했습니다: ";

    public ExpectedGameExitException(final String reason) {
        super(DEFAULT_MESSAGE + reason);
    }
}
