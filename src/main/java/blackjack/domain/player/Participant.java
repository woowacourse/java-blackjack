package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.state.State;
import blackjack.domain.state.StateFactory;
import java.util.List;

public abstract class Participant {

    private final String name;
    protected State state;

    public Participant(String name, Cards cards) {
        checkEmptyName(name);
        this.name = name;
        this.state = StateFactory.createFirstState(cards);
    }

    private void checkEmptyName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("이름은 공백이 될 수 없습니다.");
        }
    }

    public boolean isBust() {
        return state.cards().isBust();
    }

    public boolean isRunning() {
        return !state.isFinished();
    }

    public void receiveCard(Card card) {
        state = state.draw(card);
    }

    public int calculateScore() {
        return state.cards().calculateScore();
    }

    public List<Card> openAllOfCards() {
        return getCards();
    }

    public List<Card> getCards() {
        return state.cards().getCards();
    }

    public String getName() {
        return name;
    }

    public State getState() {
        return state;
    }

    public abstract List<Card> openFirstCards();
}
