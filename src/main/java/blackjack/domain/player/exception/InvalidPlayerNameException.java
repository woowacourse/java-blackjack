package blackjack.domain.player.exception;

import blackjack.common.exception.CustomException;
import blackjack.domain.player.Dealer;

public class InvalidPlayerNameException extends CustomException {

    private static final String MESSAGE = String.format("플레이어의 이름은 '%s'이(가) 될 수 없습니다.", Dealer.NAME);

    public InvalidPlayerNameException() {
        super(MESSAGE);
    }
}
