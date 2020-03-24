package blackjack.domain.game;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Result {
    WIN("승", (dealer, player) -> dealer.compareScore(player) < 0 && !player.isBlackJack(), 1.0),
    BLACKJACK("블랙잭", (dealer, player) -> !dealer.isBlackJack() && player.isBlackJack(), 1.5),
    LOSE("패", (dealer, player) -> dealer.compareScore(player) > 0, -1.0),
    DRAW("무", (dealer, player) -> dealer.compareScore(player) == 0, 0);

    private final String name;
    private final BiPredicate<Dealer, Player> condition;
    private final double rate;

    Result(String name, BiPredicate<Dealer, Player> condition, double rate) {
        this.name = name;
        this.condition = condition;
        this.rate = rate;
    }

    public static Result of(Dealer dealer, Player player) {
        return Arrays.stream(Result.values())
                .filter( result -> result.condition.test(dealer, player))
                .findFirst()
                .orElseThrow(() -> new NullPointerException("승패를 계산할 수 없습니다."));
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }
}
