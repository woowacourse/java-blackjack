package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.state.Ready;
import blackjack.domain.state.State;
import java.util.Collections;
import java.util.List;

public class Dealer {

    private final Name name;
    private State state;

    public Dealer() {
        this.name = new Name("딜러");
        this.state = new Ready();
    }

    public boolean isReady() {
        return state.isRunning();
    }

    public boolean isFinished() {
        Cards cards = state.cards();
        return cards.totalScore() > 16;
    }

    public void hit(Card card) {
        if (!isFinished()) {
            state = state.draw(card);
        }
    }

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
