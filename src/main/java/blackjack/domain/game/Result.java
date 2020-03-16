package blackjack.domain.game;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Result {
    WIN("승", (dealer, player) -> dealer.getTotalScore() < player.getTotalScore()),
    LOSE("패", (dealer, player) -> dealer.getTotalScore() > player.getTotalScore()),
    DRAW("무", (dealer, player) -> dealer.getTotalScore() == player.getTotalScore());

    private final String name;
    private BiPredicate<Dealer, Player> condition;

    Result(String name, BiPredicate<Dealer, Player> condition) {
        this.name = name;
        this.condition = condition;
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
}
