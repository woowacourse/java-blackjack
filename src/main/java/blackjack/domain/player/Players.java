package blackjack.domain.player;

import static java.util.stream.Collectors.toMap;

import blackjack.domain.bet.BetAmount;
import blackjack.domain.bet.BetLeverage;
import blackjack.domain.bet.BetRevenue;
import blackjack.domain.card.Hands;
import blackjack.domain.dealer.Dealer;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class Players {

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players of(final List<PlayerBetAmount> playerBetAmounts, final Dealer dealer) {
        return new Players(playerBetAmounts.stream()
                .map(playerBetAmount -> Player.of(playerBetAmount, dealer))
                .toList());
    }

    public void hit(
            final Dealer dealer,
            final Predicate<PlayerName> callBefore,
            final BiConsumer<PlayerName, Hands> callAfter
    ) {
        for (final Player player : players) {
            player.hit(dealer, callBefore, callAfter);
        }
    }

    public Map<PlayerName, BetRevenue> determineBetRevenue(final Dealer dealer) {
        final Map<PlayerName, BetRevenue> playerBetRevenue = new LinkedHashMap<>();

        for (final Player player : players) {
            final BetLeverage betLeverage = player.calculateBetLeverage(dealer);
            final BetAmount betAmount = player.getBetAmount();

            playerBetRevenue.put(player.getName(), betLeverage.applyLeverage(betAmount));
        }

        return Collections.unmodifiableMap(playerBetRevenue);
    }

    public boolean isAllBust() {
        return players.stream()
                .allMatch(Player::isBust);
    }

    public Map<PlayerName, Hands> getPlayersHands() {
        return players.stream()
                .collect(toMap(Player::getName,
                        Player::getHands,
                        (v1, v2) -> v1,
                        LinkedHashMap::new));
    }
}
