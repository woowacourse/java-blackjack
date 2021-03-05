package blackjack.domain;

public class Player extends User {
    public Player(String name) {
        super(name);
    }

    public Result produceResult(User dealer) {
        if (this.isAbleToHit() && !dealer.isAbleToHit()) {
            return Result.WIN;
        }
        if (this.isAbleToHit() && !dealer.isAbleToHit()) {
            return Result.STAND_OFF;
        }
        if (!this.isAbleToHit() && dealer.isAbleToHit()) {
            return Result.LOSE;
        }
        return Result.decide(this.score(), dealer.score());
    }
}
