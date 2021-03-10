package blakcjack.exception;

import blakcjack.domain.card.CardNumber;
import blakcjack.domain.card.CardSymbol;

public class CacheMissException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "캐시에 없습니다";

    public CacheMissException() {
        super(DEFAULT_MESSAGE);
    }

    public CacheMissException(final CardSymbol cardSymbol, final CardNumber cardNumber) {
        super(String.format("%s (%s, %s)", DEFAULT_MESSAGE, cardSymbol.getName(), cardNumber.getName()));
    }
}
