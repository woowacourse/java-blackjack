package domain.user.state;

import domain.card.Card;
import domain.game.Winning;

public class Ready extends State {

    public static final String NOT_TERMINATED_YET = "게임 종료 전입니다.";

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

    @Override
    public Winning match(State dealer) {
        throw new IllegalStateException(NOT_TERMINATED_YET);
    }

    @Override
    public boolean isBlackJack() {
        throw new IllegalStateException(NOT_TERMINATED_YET);
    }

    @Override
    public boolean isBust() {
        throw new IllegalStateException(NOT_TERMINATED_YET);
    }
}
