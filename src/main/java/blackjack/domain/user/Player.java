package blackjack.domain.user;

import blackjack.domain.result.Result;

import java.util.Arrays;

public class Player extends User {
    private static final int BUST = 21;

    public Player(String name) {
        super(new Name(name));
    }

    public Result decide(Dealer dealer) {
        if (isBust()) {
            return Result.LOSE;
        }
        if (dealer.isBust() && !isBust()) {
            return Result.WIN;
        }
        return Arrays.stream(Result.values())
                .filter(value -> value.getCompareResult() == this.cards.compareTo(dealer.cards))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public boolean isHit() {
        return cards.calculateScore() <= BUST;
    }
}
