package blackjack.domain.user;

import blackjack.domain.result.Result;

import java.util.Arrays;

public class Player extends User {
    private static final int BUST = 21;

    public Player(String name) {
        super(new Name(name));
    }

    public Result decideResultByBust(Dealer dealer) {
        if (dealer.isBust() && !this.isBust()) {
            return Result.WIN;
        }
        if (!dealer.isBust() && this.isBust()) {
            return Result.LOSE;
        }
        return Result.STANDOFF;
    }

    public Result decideResultByCompare(Dealer dealer) {
        return Arrays.stream(Result.values())
                .filter(value -> value.getCompareResult() == this.cards.compareTo(dealer.cards))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }

    @Override
    public boolean isHit() {
        return cards.calculateScore() <= BUST;
    }
}
