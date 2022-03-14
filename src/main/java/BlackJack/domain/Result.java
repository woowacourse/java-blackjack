package BlackJack.domain;

import BlackJack.domain.User.Dealer;
import BlackJack.domain.User.Player;

public enum Result {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String value;

    Result(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Result judge(Dealer dealer, Player player) {
        if (dealer.getScore() > 21 || dealer.getScore() < player.getScore() && player.getScore() <= 21) {
            return Result.WIN;
        }
        if (player.getScore() > 21 || (dealer.getScore() > player.getScore() && dealer.getScore() <= 21)) {
            return Result.LOSE;
        }
        return Result.DRAW;
    }

    public static Result reverse(Result result){
        if(result.equals(Result.WIN)){
            return Result.LOSE;
        }
        if(result.equals(Result.LOSE)){
            return Result.WIN;
        }
        return Result.DRAW;
    }

}
