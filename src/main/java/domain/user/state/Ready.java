package domain.user.state;

import domain.card.Card;

public class Ready extends State {

    public Ready() {
        super();
    }

    @Override
    public State draw(Card card) {
        cards.addCard(card);
        if (cards.isMaxScore()) {
            return new BlackJack(cards);
        }
        if (cards.isInitCompleted()) {
            return new Running(cards);
        }
        return this;
    }

    @Override
    public boolean isDrawable() {
        return true;
    }

    @Override
    public State stay() {
        throw new IllegalStateException("게임 시작 전입니다.");
    }
}
