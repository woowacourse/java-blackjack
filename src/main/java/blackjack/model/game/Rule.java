package blackjack.model.game;

import blackjack.model.player.Player;
import blackjack.model.player.Role;
import java.util.ArrayList;
import java.util.HashMap;
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

    public int calculateOptimalPoint(final Player player) {
        return player.calculatePossiblePoints().stream()
                .filter(point -> !isBust(point))
                .max(Integer::compareTo)
                .orElse(player.getMinimumPoint());
    }

    public Map<Player, List<Result>> calculateResult(final Player dealer, final List<Player> users) {
        Map<Player, List<Result>> results = new HashMap<>();
        users.forEach(
                user -> {
                    addResult(results, dealer, Result.findWinner(dealer, user, this));
                    addResult(results, user, Result.findWinner(user, dealer, this));
                }
        );

        return results;
    }

    private void addResult(final Map<Player, List<Result>> results, final Player player, final Result result) {
        results.computeIfAbsent(player, key -> new ArrayList<>()).add(result);
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
