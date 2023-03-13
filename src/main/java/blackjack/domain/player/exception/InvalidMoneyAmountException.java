package blackjack.domain.player.exception;

import blackjack.common.exception.CustomException;

public class InvalidMoneyAmountException extends CustomException {

    private static final String MESSAGE = "배팅 금액은 1,000원 이상 1,000,000원 이하여야 합니다.";

    public InvalidMoneyAmountException() {
        super(MESSAGE);
    }
}
