package domain.game;

import domain.card.Card;

public class Bust {

    public void draw(final Card card) {
        throw new IllegalStateException("카드를 뽑을 수 없는 상태");
    }
}
