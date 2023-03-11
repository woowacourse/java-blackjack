package domain.user.state;

import domain.card.Card;
import domain.game.Winning;
import domain.user.Cards;
import java.util.List;

public abstract class State {

    public static final String NOT_TERMINATED_YET = "게임 종료 전입니다.";

    protected Cards cards;

    protected State() {
        this.cards = new Cards();
    }

    protected State(Cards cards) {
        this.cards = cards;
    }

    public abstract State draw(Card card);

    public boolean isDrawable() {
        return true;
    }

    public boolean isRunning() {
        return false;
    }

    public State stay() {
        throw new IllegalStateException("게임 시작 전입니다.");
    }

    public Winning match(State dealer) {
        throw new IllegalStateException(NOT_TERMINATED_YET);
    }

    public boolean isBlackJack() {
        throw new IllegalStateException(NOT_TERMINATED_YET);
    }

    public boolean isBust() {
        throw new IllegalStateException(NOT_TERMINATED_YET);
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public int getScore() {
        return cards.getSumOfScores();
    }
}
