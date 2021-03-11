package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Gamer extends Player {

    private static final int DRAW_STANDARD = 21;

    public Gamer(String name, Cards cards) {
        super(name, cards);
    }

    @Override
    public void addCard(Card card) {
        if (calculateScore() >= DRAW_STANDARD) {
            throw new IllegalArgumentException("[ERROR] 플레이어의 점수가 21을 초과하여 카드를 추가할 수 없습니다.");
        }
        cards.addCard(card);
    }

    @Override
    public boolean canDraw() {
        return calculateScore() < DRAW_STANDARD;
    }

    @Override
    public boolean isDealer() {
        return false;
    }
}
