package constant;

import domain.participant.Dealer;
import domain.participant.Player;

public enum Result {
    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    ;

    private final String result;

    Result(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public static Result from(Dealer dealer, Player player) {
        if (dealer.isBust() && player.isBust()) {
            return Result.LOSE;
        }
        if (dealer.isBust() && !player.isBust()) {
            return Result.WIN;
        }
        if (player.isBust()) {
            return Result.LOSE;
        }
        if (player.calculateScore() > dealer.calculateScore()) {
            return Result.WIN;
        }
        if (player.calculateScore() == dealer.calculateScore()) {
            return Result.DRAW;
        }
        return Result.LOSE;
    }
}
