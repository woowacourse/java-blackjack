package blackjack.domain.state;

import blackjack.domain.card.Card;

public class Bust implements Status {

    private static final String ERROR_MESSAGE_INVALID_DRAW = "카드를 받을 수 없습니다.";

    @Override
    public Status draw(Card card) {
        throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_DRAW);
    }
}
