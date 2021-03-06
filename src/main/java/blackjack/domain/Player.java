package blackjack.domain;

public class Player extends User {
    public Player(String name) {
        super(name);
    }

    public Result produceResult(User dealer) {
        return Result.decide(this, dealer);
    }
}
