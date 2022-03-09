package blackjack.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Result {

    WIN((dealer, gamer) -> dealer.calculateResult() < gamer.calculateResult()),
    DRAW((dealer, gamer) -> dealer.calculateResult() == gamer.calculateResult()),
    LOSE((dealer, gamer) -> dealer.calculateResult() > gamer.calculateResult());

    private final BiPredicate<Player, Player> predicate;

    Result(BiPredicate<Player, Player> predicate) {
        this.predicate = predicate;
    }

    public static Result findResult(final Player dealer, final Player gamer){
        return Arrays.stream(values())
                .filter((result) -> result.predicate.test(dealer, gamer))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하는 결과가 없습니다."));
    }
}
