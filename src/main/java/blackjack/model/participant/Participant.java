package blackjack.model.participant;

import blackjack.model.card.CardDeck;
import blackjack.model.game.GameSign;
import blackjack.model.game.TurnProgress;
import blackjack.model.state.Ready;
import blackjack.model.state.State;
import java.util.List;

public abstract class Participant {
    protected final String name;
    protected State state;

    protected Participant(final String name) {
        checkEmpty(name);
        this.name = name;
        this.state = new Ready();
    }

    private void checkEmpty(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 이름은 공백이거나 없을 수 없습니다.");
        }
    }

    public void drawFrom(final CardDeck cardDeck) {
        while (this.state.isReady()) {
            this.state = this.state.add(cardDeck.draw());
        }
    }

    public abstract void hitFrom(final CardDeck cardDeck, final GameSign gameSign, final TurnProgress turnProgress);

    public abstract double getProfit(final Participant otherParticipant);

    public String getName() {
        return name;
    }

    public List<String> getCards() {
        return state.getCards();
    }

    public int getScore() {
        return state.getScore();
    }
}
