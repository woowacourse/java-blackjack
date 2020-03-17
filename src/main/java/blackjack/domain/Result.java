package blackjack.domain;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.exception.ResultNotFoundException;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Result {
    WIN("승", (dealer, player) -> dealer.compareScoreWith(player) < 0),
    LOSE("패", (dealer, player) -> dealer.compareScoreWith(player) > 0),
    DRAW("무승부", (dealer, player) -> dealer.compareScoreWith(player) == 0);

    private String name;
    private BiPredicate<Dealer, Player> condition;

    Result(String name, BiPredicate<Dealer, Player> condition) {
        this.name = name;
        this.condition = condition;
    }

    public static Result of(Dealer dealer, Player player) {
        return Arrays.stream(Result.values()).filter(result -> result.condition.test(dealer, player))
                .findFirst()
                .orElseThrow(() -> new ResultNotFoundException("승패를 계산할 수 없습니다"));
    }

    public String getName() {
        return name;
    }
}
