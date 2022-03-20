package blakjack.domain.state.running;

import blakjack.domain.Chip;
import blakjack.domain.PrivateArea;
import blakjack.domain.card.Card;
import blakjack.domain.state.State;
import blakjack.domain.state.finished.Blackjack;

public final class Init extends Running {
    private static final int CARD_COUNT_THRESHOLD = 1;
    private static final String CANNOT_STAY_MESSAGE = "카드가 초기화되지 않았습니다.";

    public Init(final PrivateArea privateArea, final Chip chip) {
        super(privateArea, chip);
    }

    @Override
    public State draw(final Card card) {
        privateArea.addCard(card);
        if (privateArea.isBlackjack()) {
            return new Blackjack(privateArea, chip);
        }
        if (privateArea.getCardsSize() > CARD_COUNT_THRESHOLD) {
            return new Hit(privateArea, chip);
        }
        return new Init(privateArea, chip);
    }

    @Override
    public State stay() {
        throw new IllegalStateException(CANNOT_STAY_MESSAGE);
    }
}
