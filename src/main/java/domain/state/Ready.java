package domain.state;

import domain.card.Card;

import java.util.List;

public class Ready implements State {

    @Override
    public State draw(Card card) {
        return new Hit(card);
    }

    @Override
    public List<Card> cards() {
        throw new IllegalStateException("카드가 없습니다.");
    }
}
