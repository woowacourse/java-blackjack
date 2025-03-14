package blackjack.model.game;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import blackjack.model.card.Cards;
import blackjack.model.player.Player;

public class BlackJackRule {

    public static final int BLACK_JACK_POINT = 21;
    private static final Predicate<Player> DEALER_DRAW_PREDICATE = dealer ->
            dealer.getMinimumPoint() < 17;
    private static final Predicate<Player> USER_DRAW_PREDICATE = user ->
            user.getMinimumPoint() < BLACK_JACK_POINT;

    public boolean canDrawMoreCard(final Player player) {
        if (player.isDealer()) {
            return DEALER_DRAW_PREDICATE.test(player);
        }
        return USER_DRAW_PREDICATE.test(player);
    }

    public Cards openInitialCards(final Player player) {
        Cards playerCards = player.getCards();
        if (player.isDealer()) {
            return new Cards(playerCards.getFirst());
        }
        return playerCards;
    }

    public Cards openAllCards(final Player player) {
        return player.getCards();
    }

    public int calculateOptimalPoint(final Player player) {
        return player.calculatePossiblePoints().stream()
                .filter(point -> !isBust(point))
                .max(Integer::compareTo)
                .orElse(player.getMinimumPoint());
    }

    public Map<Player, Map<GameResult, Integer>> calculateResult(final Player dealer, final List<Player> users) {
        Map<Player, Map<GameResult, Integer>> results = new LinkedHashMap<>();
        results.put(dealer, new LinkedHashMap<>(GameResult.getResultBoard()));
        users.forEach(
                user -> {
                    results.put(user, new LinkedHashMap<>(GameResult.getResultBoard()));
                    addResult(results, dealer, GameResult.calculateResult(dealer, user, this));
                    addResult(results, user, GameResult.calculateResult(user, dealer, this));
                }
        );

        return results;
    }

    private void addResult(final Map<Player, Map<GameResult, Integer>> results, final Player player, final GameResult gameResult) {
        Map<GameResult, Integer> currentResults = results.get(player);
        currentResults.put(gameResult, currentResults.get(gameResult) + 1);
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
