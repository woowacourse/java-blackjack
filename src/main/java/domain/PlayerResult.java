package domain;


import java.util.Objects;

public class PlayerResult extends Player implements ParticipantResult {

    private final GameResult gameResult;

    public PlayerResult(String name, GameResult gameResult) {
        super(name);
        this.gameResult = gameResult;
    }

    @Override
    public GameResult get() {
        return gameResult;
    }

    @Override
    public void add(GameResult gameResult) {
        return;
    }

    @Override
    public String toString() {
        return super.getName() + ": " + gameResult.getKoreanName();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        PlayerResult that = (PlayerResult) o;
        return gameResult == that.gameResult;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), gameResult);
    }
}
