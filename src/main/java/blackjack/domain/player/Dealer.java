package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Dealer extends Player {
    private static final String DEALER_DRAW_ERROR = "[ERROR] 16점 이상이라 드로우할 수 없습니다.";
    private static final String DEALER_NAME = "딜러";
    private static final int DRAW_MAXIMUM_SCORE = 16;

    public Dealer(Cards cards) {
        super(new Name(DEALER_NAME), cards);
    }

    @Override
    public boolean canDraw() {
        return (!state.isFinished()) && (calculateScore() <= DRAW_MAXIMUM_SCORE);
    }

    @Override
    public void draw(Card card) {
        if (!canDraw()) {
            throw new IllegalStateException(DEALER_DRAW_ERROR);
        }
        state = state.draw(card);
    }
}
