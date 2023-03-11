package domain.user.state;

import domain.card.Card;
import domain.user.Cards;

public abstract class Terminated extends State {

    protected Terminated(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
        throw new IllegalStateException("더 이상 카드를 받을 수 없습니다.");
    }

    @Override
    public boolean isDrawable() {
        return false;
    }
}
