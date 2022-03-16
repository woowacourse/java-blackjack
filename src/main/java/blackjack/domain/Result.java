package blackjack.domain;

import static java.util.stream.Collectors.toMap;

import blackjack.domain.user.Player;
import blackjack.domain.user.User;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;

public enum Result {

    WIN(1.5, (
            (player, dealer) -> player.isBlackJack())
    ),
    PRINCIPAL(1.0, (
            (player, dealer) -> ((player.getScore() == 21 && dealer.getScore() == 21) || (dealer.isBust())
            || (!player.isBust() && !dealer.isBust() && player.getScore() > dealer.getScore())))
    ),
    LOSS(-1.0, (
            (player, dealer) -> ((!player.isBust() && !dealer.isBust())
                    && (player.getScore() < dealer.getScore())))
    );

    private final Double rate;
    private final BiPredicate<User, User> biPredicate;

    Result(Double rate, BiPredicate<User, User> biPredicate) {
        this.rate = rate;
        this.biPredicate = biPredicate;
    }

    public static Map<Player, Double> decideResult(List<User> players, User dealer) {
        return players.stream()
                .collect(toMap(
                        user -> (Player) user, player -> findResult(player, dealer).getRate()
                ));
    }

    private static Result findResult(User player, User dealer) {
        return Arrays.stream(values())
                .filter(value -> value.biPredicate.test(player, dealer))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("결과를 찾을 수 없습니다."));
    }

    public Double getRate() {
        return rate;
    }
}
