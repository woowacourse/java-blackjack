package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Gamer extends Player {
    private static final String PLAYER_SCORE_RANGE_ERROR = "[ERROR] 플레이어의 점수가 21을 초과하여 카드를 추가할 수 없습니다.";

    public Gamer(String name, Cards cards) {
        super(name, cards);
    }

    @Override
    public void addCard(Card card) {
        if (calculateScore() >= 21) {
            throw new IllegalArgumentException(PLAYER_SCORE_RANGE_ERROR);
        }
        cards.addCard(card);
    }

    @Override
    public boolean canDraw() {
        return calculateScore() < 21;
    }
}
