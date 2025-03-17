package blackjack.model.game;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import blackjack.model.card.Cards;
import blackjack.model.player.Player;
import blackjack.model.player.User;

public class BlackJackRule {

    private static final Predicate<Player> DEALER_DRAW_PREDICATE = dealer -> dealer.calculatePoint() < 17;
    private static final Predicate<Player> USER_DRAW_PREDICATE = user -> user.calculatePoint() < 21;

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

    public Map<Player, Map<GameResult, Integer>> calculateResult(final Player dealer, final List<User> users) {
        Map<Player, Map<GameResult, Integer>> results = new LinkedHashMap<>();
        results.put(dealer, new LinkedHashMap<>());
        users.forEach(
                user -> {
                    results.put(user, new LinkedHashMap<>());
                    addResult(results, dealer, GameResult.calculateResult(dealer, user));
                    addResult(results, user, GameResult.calculateResult(user, dealer));
                }
        );
        return results;
    }

    private void addResult(final Map<Player, Map<GameResult, Integer>> results, final Player player,
                           final GameResult gameResult) {
        Map<GameResult, Integer> currentResults = results.get(player);
        currentResults.put(gameResult, currentResults.getOrDefault(gameResult, 0) + 1);
    }

}
