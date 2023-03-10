package blackjack.domain.player.exception;

import blackjack.common.exception.CustomException;

public class DealerNotFoundException extends CustomException {

    private static final String MESSAGE = "딜러를 찾을 수 없습니다.";

    public DealerNotFoundException() {
        super(MESSAGE);
    }
}
