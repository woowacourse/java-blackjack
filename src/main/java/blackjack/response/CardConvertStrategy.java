package blackjack.response;

import blackjack.domain.Card;

public interface CardConvertStrategy {

    String convert(Card card);
}
