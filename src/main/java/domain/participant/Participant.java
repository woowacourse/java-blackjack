package domain.participant;

import domain.Score;
import domain.state.State;
import domain.card.Card;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public abstract class Participant {
    private final Name name;
    private State state;

    protected Participant(Name name, State state) {
        this.name = name;
        this.state = state;
    }

    public void draw(Card card) {
        this.state = state.draw(card);
    }

    public Score getScore() {
        return state.getScore();
    }

    public void stay() {
        this.state = state.stay();
    }

    public List<Card> getCards() {
        return state.cards();
    }

    public Card getFirstCard() {
        List<Card> cards = getCards();
        if (cards.isEmpty()) {
            throw new IllegalStateException("보유한 카드가 없습니다.");
        }
        return cards.getFirst();
    }

    public String getName() {
        return name.value();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public BigDecimal calculateProfitRate(Participant other) {
        return state.calculateProfitRate(other.state);
    }

    public abstract boolean canReceive();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant participant = (Participant) o;
        return Objects.equals(name, participant.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
