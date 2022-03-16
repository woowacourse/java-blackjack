package blackjack.domain;

import blackjack.domain.user.User;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

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

    private final Double reward;
    private final BiPredicate<User, User> biPredicate;

    Result(Double reward, BiPredicate<User, User> biPredicate) {
        this.reward = reward;
        this.biPredicate = biPredicate;
    }

    public static Map<String, Result> getMap(List<User> players, User dealer) {
        return players.stream()
                .collect(Collectors.toMap(
                        User::getName,
                        player -> findResult(player, dealer)
                ));
    }

    private static Result findResult(User player, User dealer) {
        return Arrays.stream(values())
                .filter(value -> value.biPredicate.test(player, dealer))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("결과를 찾을 수 없습니다."));
    }

    public Double getReward() {
        return reward;
    }
}
