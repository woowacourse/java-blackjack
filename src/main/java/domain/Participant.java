package domain;

import domain.state.GameState;
import java.util.List;

public abstract class Participant {
    protected final GameState gameState;
    protected final ParticipantName participantName;

    protected Participant(String name, GameState gameState) {
        this.participantName = new ParticipantName(name);
        this.gameState = gameState;
    }

    public abstract List<Card> showInitialCard();

    public String getName() {
        return participantName.name();
    }

    public List<Card> showOwnCards() {
        return gameState.showOwnCards();
    }

    public int getOwnCardsSum() {
        return this.gameState.getCardsSum();
    }
}