package domain;


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
}
