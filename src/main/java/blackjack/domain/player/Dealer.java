package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Dealer extends Player {
    private static final String DEALER_SCORE_RANGE_ERROR = "[ERROR] 딜러의 점수가 16을 초과하여 카드를 추가할 수 없습니다.";
    private static final String DEALER_NAME = "딜러";
    private static final int DRAW_MAXIMUM_SCORE = 16;

    public Dealer(Cards cards) {
        super(new Name(DEALER_NAME), cards);
    }

    @Override
    public void addCard(Card card) {
        validateDealerCardScore();
        cards.addCard(card);
    }

    private void validateDealerCardScore() {
        if (calculateScore() > DRAW_MAXIMUM_SCORE) {
            throw new IllegalArgumentException(DEALER_SCORE_RANGE_ERROR);
        }
    }

    @Override
    public boolean canDraw() {
        return calculateScore() <= DRAW_MAXIMUM_SCORE;
    }
}
