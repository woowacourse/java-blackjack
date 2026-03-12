package domain;

import domain.state.GameState;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class Player extends Participant {
    private Player(String name, GameState gameState) {
        super(name, gameState);
    }

    public static Player from(String name, GameState gameState) {
        return new Player(name, gameState);
    }

    public Player hit(Supplier<Card> cardSupplier) {
        GameState newGameState = gameState.hit(cardSupplier);
        return new Player(
                this.participantName.name(),
                newGameState
        );
    }

    public Player stand() {
        GameState newStandGameState = gameState.stay();
        return new Player(
                this.participantName.name(),
                newStandGameState
        );
    }

    public boolean isFinished() {
        return gameState.isFinished();
    }

    public GameResult calculateGameResult(GameState dealerGameState) {
        return gameState.compare(gameState, dealerGameState);
    }

    @Override
    public List<Card> showInitialCard() {
        return super.showOwnCards();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Player player)) {
            return false;
        }

        return Objects.equals(getName(), player.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}