package blackjack.domain;

import static java.util.stream.Collectors.toMap;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.User;
import blackjack.domain.vo.BettingMoney;
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
            (player, dealer) -> ((player.isBlackJackScore() && dealer.isBlackJackScore()) || (dealer.isBust())
            || (!player.isBust() && !dealer.isBust() && player.getScore() > dealer.getScore())
            || (!player.isBust() && !dealer.isBust() && player.getScore() == dealer.getScore())))
    ),
    LOSS(-1.0, (
            (player, dealer) -> ((!player.isBust() && !dealer.isBust())
                    && (player.getScore() < dealer.getScore())
            || (player.isBust() && !dealer.isBust())))
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
                        player -> player.getName(), player -> findResult(player, dealer),
                        (e1, e2) -> e1,
                        LinkedHashMap::new)
                );
    }

    private static int findResult(Player player, Dealer dealer) {
        BettingMoney bettingMoney = player.getBettingMoney();

        Result result = Arrays.stream(values())
                .filter(value -> value.biPredicate.test(player, dealer))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("결과를 찾을 수 없습니다."));

        return bettingMoney.calculateRevenue(result.getRate());
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
