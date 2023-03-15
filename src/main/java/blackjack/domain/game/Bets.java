package blackjack.domain.game;

import blackjack.domain.user.Player;
import blackjack.domain.user.User;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Bets {

    private final Map<User, Money> bets = new LinkedHashMap<>();

    public void betting(final Player player, final Money money) {
        bets.put(player, money);
    }


    public void updatePrizes(final Map<User, Result> result) {
        bets.keySet().stream().forEach((user) -> {
            updateUserPrize(user, result.get(user));
        });
    }

    private void updateUserPrize(final User user, final Result userResult) {
        Money money = bets.get(user);
        final Map<GameResult, Integer> userGameResult = userResult.getResult();
        final List<GameResult> positiveResult = userGameResult.keySet()
                .stream()
                .filter(gameResult -> userGameResult.get(gameResult) != 0)
                .collect(Collectors.toList());

        for (GameResult result : positiveResult) {
            money = money.calculatePrize(result);
        }

        bets.put(user, money);
    }

    public Money getDealerProfit() {
        Money dealerMoney = Money.getMin();

        for (User user : bets.keySet()) {
            dealerMoney = dealerMoney.add(bets.get(user));
        }

        return dealerMoney.multiply(-1);
    }

    public Map<User, Money> getBets() {
        return new LinkedHashMap<>(bets);
    }
}
