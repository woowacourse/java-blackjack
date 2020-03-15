package model;

import java.util.Arrays;
import model.user.Dealer;
import model.user.Player;

public enum Result {
    DRAW("무") {
        boolean isResult(Dealer dealer, Player player) {
            return (player.isBust() && dealer.isBust()) || dealer.getScore() == player.getScore();
        }
    },
    WIN("승") {
        boolean isResult(Dealer dealer, Player player) {
            return player.isBust() || (!dealer.isBust() && dealer.getScore() > player.getScore());
        }
    },
    LOSE("패") {
        boolean isResult(Dealer dealer, Player player) {
            return dealer.isBust() || (!player.isBust() && dealer.getScore() < player.getScore());
        }
    };

    String result;

    Result(String result) {
        this.result = result;
    }

    abstract boolean isResult(Dealer dealer, Player player);

    public static Result calculateResult(Dealer dealer, Player player) {
        return Arrays.stream(Result.values())
            .filter(result -> result.isResult(dealer, player))
            .findFirst()
            .get();
    }

    public static Result oppositeResult(Result result) {
        if (isWin(result)) {
            return LOSE;
        }
        if (isLose(result)) {
            return WIN;
        }
        return DRAW;
    }

    private static boolean isWin(final Result result) {
        return result == WIN;
    }

    private static boolean isLose(final Result result) {
        return result == LOSE;
    }

    @Override
    public String toString() {
        return result;
    }
}
