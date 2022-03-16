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
            (player, dealer) -> ((player.calculateScore() == 21 && dealer.calculateScore() == 21) || (dealer.isBust())))
    ),
    LOSS(-1.0, (
            (player, dealer) -> ((!player.isBust() && !dealer.isBust())
                    && (player.calculateScore() < dealer.calculateScore())))
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

    private Double getRate() {
        return rate;
    }
}
