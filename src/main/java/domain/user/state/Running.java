package domain.user.state;

import domain.card.Card;
import domain.game.Winning;
import domain.user.Cards;

public class Running extends State {

    public static final String NOT_TERMINATED_YET = "게임 종료 전입니다.";

    protected Running(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
        cards.addCard(card);
        if (cards.isMaxScore()) {
            return new Stay(cards);
        }
        if (cards.isBust()) {
            return new Bust(cards);
        }
        return this;
    }

    @Override
    public boolean isDrawable() {
        return true;
    }

    @Override
    public State stay() {
        return new Stay(cards);
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
