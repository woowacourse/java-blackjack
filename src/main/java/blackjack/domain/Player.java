package blackjack.domain;

import java.util.Arrays;

public class Player extends User {
    public Player(String name) {
        super(new Name(name));
    }

    public Result produceResult(User dealer) {
//        if (!dealer.isAbleToHit() && this.isAbleToHit()) {
//            return Result.WIN;
//        }
//        if (!dealer.isAbleToHit() && this.isAbleToHit()) {
//            return Result.STAND_OFF;
//        }
//        if (dealer.isAbleToHit() && !this.isAbleToHit()) {
//            return Result.LOSE;
//        }
        return Result.LOSE;
    }
}
