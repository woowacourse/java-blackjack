package blackjack.domain;

import static java.util.stream.Collectors.toMap;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.User;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;

public enum Result {

    BLACKJACK(1.5, (
            (player, dealer) -> player.isBlackJack())
    ),
    WIN(1.0, (
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

    public static Map<String, Integer> calculateRevenue(List<Player> players, Dealer dealer) {
        Map<String, Integer> revenue = new LinkedHashMap<>();

        Map<String, Integer> playerRevenue = calculatePlayerRevenue(players, dealer);

        revenue.put(dealer.getName(), calculateDealerRevenue(playerRevenue));

        revenue.putAll(playerRevenue);

        return revenue;
    }

    private static LinkedHashMap<String, Integer> calculatePlayerRevenue(List<Player> players, Dealer dealer) {
        return players.stream()
                .collect(toMap(
                        player -> player.getName(), player -> player.getRevenue(findResult(player, dealer).getRate()),
                        (e1, e2) -> e1,
                        LinkedHashMap::new)
                );
    }

    private static Result findResult(User player, User dealer) {
        return Arrays.stream(values())
                .filter(value -> value.biPredicate.test(player, dealer))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("결과를 찾을 수 없습니다."));
    }

    private static int calculateDealerRevenue(Map<String, Integer> playerRevenue) {
        return - (playerRevenue.values()
                .stream()
                .mapToInt(value -> value).sum());
    }

    public Double getRate() {
        return rate;
    }
}
