package domain;

import domain.state.GameState;
import java.util.List;
import java.util.Objects;

public abstract class Participant {
    protected final GameState gameState;
    protected final ParticipantName participantName;
    protected final BetAmount betAmount;

    protected Participant(String name, GameState gameState, BetAmount betAmount) {
        this.participantName = new ParticipantName(name);
        this.gameState = gameState;
        this.betAmount = betAmount;
    }

    public abstract List<Card> showInitialCard();

    public String getName() {
        return participantName.name();
    }

    public boolean isBet() {
        return !Objects.equals(betAmount, BetAmount.empty());
    }

    public List<Card> showOwnCards() {
        return gameState.showOwnCards();
    }

    public int getOwnCardsSum() {
        return this.gameState.getCardsSum();
    }
}