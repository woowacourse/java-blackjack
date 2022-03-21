package blackjack.domain.participant;

import blackjack.domain.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.state.Blackjack;
import blackjack.domain.state.Bust;
import blackjack.domain.state.State;
import java.util.List;
import java.util.Objects;

public abstract class Participant {

    private final Name name;
    private State state;

    protected Participant(Name name, State state) {
        Objects.requireNonNull(name, "[ERROR] 이름은 null일 수 없습니다.");
        Objects.requireNonNull(state, "[ERROR] 상태는 null일 수 없습니다.");

        this.name = name;
        this.state = state;
    }

    public void hit(CardDeck deck) {
        state = state.hit(deck.draw());
    }

    public void stand() {
        state = state.stand();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public boolean isBust() {
        return state instanceof Bust;
    }

    public boolean isBlackjack() {
        return state instanceof Blackjack;
    }

    public Score getScore() {
        return state.calculateScore();
    }

    public abstract List<Card> showInitialCards();

    public List<Card> getCards() {
        return List.copyOf(state.getCards());
    }

    public String getName() {
        return name.getValue();
    }
}
