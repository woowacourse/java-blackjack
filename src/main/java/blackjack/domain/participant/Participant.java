package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.state.State;
import java.util.Collections;
import java.util.List;

public abstract class Participant {

    private final Name name;
    protected State state;

    protected Participant(Name name, State state) {
        this.name = name;
        this.state = state;
    }

    public boolean isReady() {
        return state.isRunning();
    }

    public void hit(Card card) {
        if (isDrawable()) {
            state = state.draw(card);
        }
    }

    public abstract boolean isDrawable();

    public int getTotalScore() {
        Cards cards = state.cards();
        return cards.totalScore();
    }

    public String getName() {
        return name.getValue();
    }

    public List<Card> getCards() {
        Cards cards = state.cards();
        return Collections.unmodifiableList(cards.getValue());
    }
}
