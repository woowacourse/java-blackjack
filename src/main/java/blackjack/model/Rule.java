package blackjack.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Rule {

    private static final Predicate<Player> DEALER_DRAWABLE_SCORE = dealer ->
            Collections.min(dealer.calculateSumOfCards(), Integer::compareTo) < 17;
    private static final Predicate<Player> USER_DRAWABLE_SCORE = user ->
            Collections.min(user.calculateSumOfCards(), Integer::compareTo) < 21;

    public boolean canPlayerDrawMoreCard(final Player player) {
        if (player.hasRole(Role.DEALER)) {
            return DEALER_DRAWABLE_SCORE.test(player);
        }
        return USER_DRAWABLE_SCORE.test(player);
    }

    public int calculatePoint(final Player player) {
        List<Integer> points = player.calculateSumOfCards();
        return points.stream()
                .filter(point -> point <= 21)
                .max(Integer::compareTo)
                .orElse(Collections.min(points));
    }

    public Map<Player, List<Result>> calculateResult(final Player dealer, final List<Player> users) {
        Map<Player, List<Result>> results = new HashMap<>();
        results.put(dealer, new ArrayList<>());
        for (Player user : users) {
            results.put(user, new ArrayList<>());
        }
        for (Player user : users) {
            addResult(results, dealer, Result.findWinner(dealer, user, this));
            addResult(results, user, Result.findWinner(user, dealer, this));
        }
        return results;
    }

    private void addResult(final Map<Player, List<Result>> results, Player player, final Result result) {
        results.get(player).add(result);
    }

    public boolean isDraw(final Player player, final Player otherPlayer) {
        int point = calculatePoint(player);
        int otherPoint = calculatePoint(otherPlayer);
        if (isBust(point) && isBust(otherPoint)) {
            return true;
        }
        return point == otherPoint;
    }

    public Player getWinner(final Player player, final Player otherPlayer) {
        if (!isDraw(player, otherPlayer)) {
            return findWinner(player, otherPlayer);
        }
        throw new IllegalStateException();
    }

    private Player findWinner(final Player player, final Player otherPlayer) {
        int point = calculatePoint(player);
        int otherPoint = calculatePoint(otherPlayer);
        if (isBust(point) && !isBust(otherPoint)) {
            return otherPlayer;
        }
        if (!isBust(point) && isBust(otherPoint)) {
            return player;
        }
        if (point < otherPoint) {
            return otherPlayer;
        }
        return player;
    }

    private boolean isBust(final int otherPoint) {
        return otherPoint > 21;
    }

}
