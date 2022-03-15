package blackJack.domain;

import blackJack.domain.User.Dealer;
import blackJack.domain.User.Player;

public enum Result {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    public static final int WIN_SCORE = 21;
    private final String printFormat;

    Result(String value) {
        this.printFormat = value;
    }

    public static Result judge(Dealer dealer, Player player) {
        if (dealer.getScore() > WIN_SCORE || dealer.getScore() < player.getScore() && player.getScore() <= WIN_SCORE) {
            return Result.WIN;
        }
        if (player.getScore() > WIN_SCORE || (dealer.getScore() > player.getScore() && dealer.getScore() <= WIN_SCORE)) {
            return Result.LOSE;
        }
        return Result.DRAW;
    }

    public static Result reverse(Result result) {
        if (result.equals(Result.WIN)) {
            return Result.LOSE;
        }
        if (result.equals(Result.LOSE)) {
            return Result.WIN;
        }
        return Result.DRAW;
    }

    public String getPrintFormat() {
        return printFormat;
    }

}
