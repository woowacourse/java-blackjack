package blackjack.domain.participant;

import blackjack.domain.BettingMoney;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.state.State;
import blackjack.domain.state.StateFactory;

import java.util.Objects;

public abstract class Participant {
    protected Nickname nickname;
    protected State state;
    protected BettingMoney bettingMoney;

    protected Participant(Nickname nickname) {
        this.nickname = nickname;
    }

    public abstract boolean canDraw();

    public void firstDraw(Card firstCard, Card secondCard) {
        this.state = StateFactory.draw(firstCard, secondCard);
    }

    public void draw(Card card) {
        state = state.draw(card);
    }

    public void stay() {
        state = state.stay();
    }

    public boolean isHit() {
        return !state.isFinished();
    }

    public final Cards getCurrentCards() {
        return state.getCards();
    }

    public final Nickname getName() {
        return nickname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Objects.equals(nickname, that.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname);
    }
}
