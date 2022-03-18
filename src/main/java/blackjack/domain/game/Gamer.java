package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.state.Blackjack;
import blackjack.domain.state.Bust;
import blackjack.domain.state.Ready;
import blackjack.domain.state.State;

import java.util.List;

public abstract class Gamer {

    protected final String name;
    protected State state = new Ready();

    protected Gamer(final String name) {
        this.name = name;
    }

    public PlayingCards openCards() {
        return state.playingCards();
    }

    public int sumOfCards() {
        return state.cardTotal();
    }

    // TODO: 삭제
    public void bet(final int betting) {
        state.bet(String.valueOf(betting));
    }

    public void deal(final List<Card> cards) {
        for (Card card : cards) {
            state = state.draw(card);
        }
    }

    public void draw(final Card card) {
        state = state.draw(card);
    }

    public void stay() {
        state = state.stay();
    }

    public boolean isRunning() {
        return state.isRunning();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public boolean isBlackjack() {
        // TODO: 방식 수정
        return state instanceof Blackjack;
    }

    public boolean isBust() {
        // TODO: 방식 수정
        return state instanceof Bust;
    }

    public String getName() {
        return name;
    }

    abstract boolean isDrawable();
}
