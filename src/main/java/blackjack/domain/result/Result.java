package blackjack.domain.result;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiPredicate;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;

public enum Result {

    WIN("승", (dealer, player) -> !player.isBust() && dealer.calculateScore() < player.calculateScore()),
    DRAW("무", (dealer, player) -> dealer.calculateScore() == player.calculateScore()),
    LOSE("패", (dealer, player) -> !dealer.isBust() && dealer.calculateScore() > player.calculateScore()),
    ;

    private final String value;
    private final BiPredicate<Gamer, Gamer> biPredicate;

    Result(String value, BiPredicate<Gamer, Gamer> biPredicate) {
        this.value = value;
        this.biPredicate = biPredicate;
    }

    public static Map<Gamer, Result> judge(Dealer dealer, Player player) {
        Map<Gamer, Result> judgeResult = new LinkedHashMap<>();
        Result judge = Arrays.stream(Result.values())
            .filter(result -> result.biPredicate.test(dealer, player))
            .findFirst()
            .orElseThrow();

        judgeResult.put(dealer, judge.reverse());
        judgeResult.put(player, judge);
        return judgeResult;
    }

    private Result reverse() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public String getValue() {
        return value;
    }
}
