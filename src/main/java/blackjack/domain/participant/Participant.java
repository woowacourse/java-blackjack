package blackjack.domain.participant;

import blackjack.domain.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.state.Running;
import blackjack.domain.state.State;
import java.util.List;
import java.util.Objects;

public abstract class Participant {

    private final Name name;
    private State state;

    protected Participant(Name name, List<Card> cards) {
        Objects.requireNonNull(name, "[ERROR] 이름은 null일 수 없습니다.");
        Objects.requireNonNull(cards, "[ERROR] 카드들은 null일 수 없습니다.");

        this.name = name;
        this.state = Running.start(cards);
    }

    public void hit(CardDeck deck) {
        state = state.hit(deck.draw());
    }

    public void stand() {
        state = state.stand();
    }

    public boolean isBust() {
        return state.isBust();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public boolean isBlackjack() {
        return state.isBlackjack();
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
