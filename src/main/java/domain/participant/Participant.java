package domain.participant;

import domain.card.Card;
import domain.card.CardBundle;
import domain.game.HandState;
import java.util.List;
import java.util.Objects;

public abstract class Participant {
    private final PlayerName playerName;
    private final CardBundle cardBundle;
    private HandState state;

    protected Participant(String name) {
        this.playerName = new PlayerName(name);
        this.cardBundle = new CardBundle();
    }

    public boolean canHit() {
        return state.canHit();
    }

    public void addCard(Card card) {
        cardBundle.addCard(card);
        this.state = cardBundle.resolveState();
    }

    public List<Card> getCards() {
        return cardBundle.getCards();
    }

    public String getName() {
        return playerName.getName();
    }

    public int getScore() {
        return cardBundle.calculateScore();
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
