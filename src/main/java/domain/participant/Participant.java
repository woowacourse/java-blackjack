package domain.participant;

import domain.card.Card;
import domain.card.CardBundle;
import domain.game.HandState;
import domain.game.Hit;
import java.util.List;
import java.util.Objects;

public abstract class Participant {
    private final PlayerName playerName;
    private HandState state;

    protected Participant(String name) {
        this.playerName = new PlayerName(name);
        this.state = new Hit(new CardBundle());
    }

    public boolean canHit() {
        return state.canHit();
    }

    public void addCard(Card card) {
        this.state = state.draw(card);
    }

    public void stay() {
        this.state = state.stay();
    }

    public List<Card> getCards() {
        return state.cards();
    }

    public String getName() {
        return playerName.getName();
    }

    public int getScore() {
        return state.score();
    }

    public HandState getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Participant that = (Participant) o;
        return Objects.equals(playerName, that.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(playerName);
    }
}
