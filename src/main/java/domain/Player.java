package domain;

import domain.state.GameState;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class Player extends Participant {
    private final BetAmount betAmount;

    private Player(String name, GameState gameState, BetAmount betAmount) {
        super(name, gameState);
        this.betAmount = betAmount;
    }

    public static Player from(String name, GameState gameState) {
        return new Player(name, gameState, BetAmount.empty());
    }

    public Player bet(String betAmountValue) {
        return new Player(
                this.participantName.name(),
                this.gameState,
                BetAmount.of(betAmountValue)
        );
    }

    public boolean isBet() {
        return betAmount.isBetPlaced();
    }

    public int getBetAmount() {
        return betAmount.betAmount();
    }

    public Player hit(Supplier<Card> cardSupplier) {
        GameState newGameState = gameState.hit(cardSupplier);
        return new Player(
                this.participantName.name(),
                newGameState,
                this.betAmount
        );
    }

    public Player stand() {
        GameState newStandGameState = gameState.stay();
        return new Player(
                this.participantName.name(),
                newStandGameState,
                this.betAmount
        );
    }

    public boolean isPlayable() {
        return gameState.isPlayable();
    }

    public boolean isFinished() {
        return gameState.isFinished();
    }

    public double calculateEarnMoney(Dealer dealer) {
        GameResult gameResult = calculateGameResult(dealer);
        return gameResult.getAllocation() * getBetAmount();
    }

    private GameResult calculateGameResult(Dealer dealer) {
        return GameResult.decidePlayerResult(this, dealer);
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