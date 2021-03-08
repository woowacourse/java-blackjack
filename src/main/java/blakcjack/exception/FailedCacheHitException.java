package blakcjack.exception;

import blakcjack.domain.card.CardNumber;
import blakcjack.domain.card.CardSymbol;

public class FailedCacheHitException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "캐시에 없습니다";

    public FailedCacheHitException() {
        super(DEFAULT_MESSAGE);
    }

    public FailedCacheHitException(final CardSymbol cardSymbol, final CardNumber cardNumber) {
        super(String.format("%s (%s, %s)", DEFAULT_MESSAGE, cardSymbol.getName(), cardNumber.getName()));
    }
}
