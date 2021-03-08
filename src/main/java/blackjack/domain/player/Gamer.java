package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Gamer extends Player {
    private static final String PLAYER_SCORE_RANGE_ERROR = "[ERROR] 플레이어의 점수가 21을 초과하여 카드를 추가할 수 없습니다.";
    private static final int DRAW_MAXIMUM_SCORE = 21;

    public Gamer(Name name, Cards cards) {
        super(name, cards);
    }

    @Override
    public void addCard(Card card) {
        validateGamerCardScore();
        cards.addCard(card);
    }

    private void validateGamerCardScore() {
        if (calculateScore() >= DRAW_MAXIMUM_SCORE) {
            throw new IllegalArgumentException(PLAYER_SCORE_RANGE_ERROR);
        }
    }

    @Override
    public boolean canDraw() {
        return calculateScore() < DRAW_MAXIMUM_SCORE;
    }
}
