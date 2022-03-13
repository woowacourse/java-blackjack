package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Player extends Gamer {

    private static final int DRAWABLE_NUMBER = 21;
    private static final String CAN_NOT_DRAWABLE_ERROR_MESSAGE = "%s 카드의 합이 %d을 초과했기 때문에 카드를 뽑을 수 없습니다.\n";

    public Player(String name, Cards cards) {
        super(name, cards);
    }

    @Override
    public void drawCard(Card card) {
        if (!canDraw()) {
            throw new IllegalArgumentException(String.format(CAN_NOT_DRAWABLE_ERROR_MESSAGE, name, DRAWABLE_NUMBER));
        }
        cards.add(card);
    }

    @Override
    public boolean canDraw() {
        return calculateScore() <= DRAWABLE_NUMBER;
    }
}
