package blackjack.domain.player.exception;

import blackjack.common.exception.CustomException;

public class InvalidMoneyUnitException extends CustomException {

    private static final String MESSAGE = "배팅액은 10원 단위여야 합니다.";
    public InvalidMoneyUnitException() {
        super(MESSAGE);
    }
}
