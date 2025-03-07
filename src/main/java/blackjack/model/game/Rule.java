package blackjack.model.game;

import blackjack.model.card.Cards;
import blackjack.model.player.Dealer;
import blackjack.model.player.Player;
import blackjack.model.player.Role;
import blackjack.model.player.User;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Rule {

    public static final int BLACK_JACK_POINT = 21;
    private static final Predicate<Player> DEALER_DRAW_PREDICATE = dealer ->
            dealer.getMinimumPoint() < 17;
    private static final Predicate<Player> USER_DRAW_PREDICATE = user ->
            user.getMinimumPoint() < BLACK_JACK_POINT;

    public boolean canDrawMoreCard(final Player player) {
        if (player.hasRole(Role.DEALER)) {
            return DEALER_DRAW_PREDICATE.test(player);
        }
        return USER_DRAW_PREDICATE.test(player);
    }

    public Cards openDealerCards(final Dealer dealer) {
        return new Cards(dealer.getFirstCard());
    }

    public int calculateOptimalPoint(final Player player) {
        return player.calculatePossiblePoints().stream()
                .filter(point -> !isBust(point))
                .max(Integer::compareTo)
                .orElse(player.getMinimumPoint());
    }

    public Map<Player, Map<Result, Integer>> calculateResult(final Dealer dealer, final List<User> users) {
        Map<Player, Map<Result, Integer>> results = new LinkedHashMap<>();
        results.put(dealer, new LinkedHashMap<>(Result.getResultBoard()));
        users.forEach(
                user -> {
                    results.put(user, new LinkedHashMap<>(Result.getResultBoard()));
                    addResult(results, dealer, Result.findWinner(dealer, user, this));
                    addResult(results, user, Result.findWinner(user, dealer, this));
                }
        );

        return results;
    }

    private void addResult(final Map<Player, Map<Result, Integer>> results, final Player player, final Result result) {
        Map<Result, Integer> currentResults = results.get(player);
        currentResults.put(result, currentResults.get(result) + 1);
    }

    public boolean isDraw(final Player player, final Player challenger) {
        int point = calculateOptimalPoint(player);
        int otherPoint = calculateOptimalPoint(challenger);
        if (isBust(point) && isBust(otherPoint)) {
            return true;
        }
        return point == otherPoint;
    }

    public Player getWinner(final Player player, final Player challenger) {
        if (!isDraw(player, challenger)) {
            return findWinner(player, challenger);
        }
        throw new IllegalStateException();
    }

    private Player findWinner(final Player player, final Player challenger) {
        int point = calculateOptimalPoint(player);
        int otherPoint = calculateOptimalPoint(challenger);
        if (isBust(point) && !isBust(otherPoint)) {
            return challenger;
        }
        if (!isBust(point) && isBust(otherPoint)) {
            return player;
        }
        if (point < otherPoint) {
            return challenger;
        }
        return player;
    }

    private boolean isBust(final int point) {
        return point > BLACK_JACK_POINT;
    }

}
