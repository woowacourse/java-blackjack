package domain.state;

import java.util.function.Function;

public enum Result {
    //여기서 금액 회수 or 1.5배 or 2배
    BLACKJACK("블랙잭", (n) -> n * 15 / 10),
    WIN("승", (n) -> n),
    DRAW("무", (n) -> 0),
    LOSE("패", (n) -> -n),
    ;

    private final String displayName;
    private final Function<Integer, Integer> earnCost;

    Result(String displayName, Function<Integer, Integer> earnCost) {
        this.displayName = displayName;
        this.earnCost = earnCost;
    }

    public static Result getResult(State dealerState, State playerState) {
        if (playerState instanceof Bust) {
            return Result.LOSE;
        }
        if (playerState instanceof BlackJack) {
            return Result.BLACKJACK;
        }
        if (dealerState instanceof Bust || playerState.getScore() > dealerState.getScore()) {
            return Result.WIN;
        }
        if (dealerState.getScore() > playerState.getScore()) {
            return LOSE;
        }
        if (dealerState.getScore().equals(playerState.getScore())) {
            return Result.DRAW;
        }
        throw new IllegalStateException("Result 뭔가 잘못된거같아...");
    }

    public Integer getEarnCost(int n) {
        return earnCost.apply(n);
    }
}
