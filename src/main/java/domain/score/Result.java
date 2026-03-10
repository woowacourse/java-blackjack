package domain.score;

import domain.state.Burst;
import domain.state.State;

public enum Result {
    //여기서 금액 회수 or 1.5배 or 2배
    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    ;

    private final String displayName;

    Result(String displayName) {
        this.displayName = displayName;
    }

    public static Result getResult(State dealerState, State playerState) {
        if (playerState instanceof Burst
                || !(dealerState instanceof Burst)
                && dealerState.getScore() > playerState.getScore()) {
            return Result.LOSE;
        }
        if (dealerState instanceof Burst || playerState.getScore() > dealerState.getScore()) {
            return Result.WIN;
        }
        if (dealerState.getScore().equals(playerState.getScore())) {
            return Result.DRAW;
        }
        throw new IllegalArgumentException("Result 뭔가 잘못된거같아...");
    }

    public String getDisplayName() {
        return displayName;
    }
}
