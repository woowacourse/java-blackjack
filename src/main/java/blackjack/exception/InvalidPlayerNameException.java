package blackjack.exception;

import blackjack.domain.player.Dealer;

public class InvalidPlayerNameException extends CustomException {

    private static final String MESSAGE = "플레이어의 이름은 " + Dealer.NAME + "이면 안됩니다.";

    public InvalidPlayerNameException() {
        super(MESSAGE);
    }
}
