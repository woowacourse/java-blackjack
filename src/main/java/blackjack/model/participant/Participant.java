package blackjack.model.participant;

import blackjack.model.card.Card;
import blackjack.model.game.DrawStrategy;
import blackjack.model.game.GameSign;
import blackjack.model.game.TurnProgress;
import blackjack.model.state.State;
import blackjack.model.state.running.Ready;
import java.util.List;

public abstract class Participant {
    protected final String name;
    protected State state;

    protected Participant(final String name) {
        this.name = name;
        this.state = new Ready();
    }

    public void drawFrom(final DrawStrategy drawStrategy) {
        while (this.state.isReady()) {
            this.state = this.state.add(drawStrategy.draw());
        }
    }

    public abstract void hitFrom(final DrawStrategy drawStrategy, final GameSign gameSign,
                                 final TurnProgress turnProgress);

    public abstract double getProfit(final Participant otherParticipant);

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return state.getCards();
    }

    public int getScore() {
        return state.getScore();
    }

    public List<Card> getFirstCard() {
        return List.of(state.getCards().get(0));
    }
}
