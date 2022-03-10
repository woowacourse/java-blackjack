package blackjack.domain;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.User;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public enum Result {

    WIN("승", (player, dealer) -> (dealer.isBust() && !player.isBust()) || player.isWinTo(dealer)),
    TIE("무", (player, dealer) -> player.isWinTo(dealer) && dealer.isWinTo(player)),
    LOSS("패", (player, dealer) -> player.isBust() || dealer.isWinTo(player));


    private final String name;
    private final BiPredicate<Player, Dealer> biPredicate;

    Result(String name, BiPredicate<Player, Dealer> biPredicate) {
        this.name = name;
        this.biPredicate = biPredicate;
    }

    public static Map<String, Result> getMap(List<Player> players, Dealer dealer) {
        return players.stream()
                .collect(Collectors.toMap(
                        User::getName,
                        player -> findResult(player, dealer)
                ));
    }

    private static Result findResult(Player player, Dealer dealer) {
        return Arrays.stream(values())
                .filter(value -> value.biPredicate.test(player, dealer))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("결과를 찾을 수 없습니다."));
    }

    public Result reverseResult() {
        if (this == Result.LOSS) {
            return Result.WIN;
        }
        if (this == Result.WIN) {
            return Result.LOSS;
        }
        return Result.TIE;
    }

    public String getName() {
        return name;
    }
}
