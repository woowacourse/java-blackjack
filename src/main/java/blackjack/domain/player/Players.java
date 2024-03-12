package blackjack.domain.player;

import static java.util.stream.Collectors.toMap;

import blackjack.domain.card.Hands;
import blackjack.domain.dealer.Dealer;
import blackjack.domain.player.bet.BetAmount;
import blackjack.domain.player.bet.BetRevenue;
import blackjack.domain.player.bet.BetStatus;
import blackjack.domain.rule.state.State;
import blackjack.exception.NeedRetryException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class Players {

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players of(final List<PlayerName> playerNames, final Dealer dealer) {
        validateDuplicate(playerNames);

        return new Players(playerNames.stream()
                .map(name -> Player.of(name, dealer))
                .toList());
    }

    private static void validateDuplicate(final List<PlayerName> playerNames) {
        if (Set.copyOf(playerNames).size() != playerNames.size()) {
            throw new NeedRetryException("중복된 이름의 참여자는 참여할 수 없습니다.");
        }
    }

    public void saveBetAmount(final Map<PlayerName, BetAmount> playerBetAmounts) {
        players.forEach(player -> player.setBetAmount(playerBetAmounts.get(player.getName())));
    }

    public void hit(
            final Dealer dealer,
            final Predicate<PlayerName> isHitInput,
            final BiConsumer<PlayerName, Hands> printHands
    ) {
        for (Player player : players) {
            player.hit(dealer, isHitInput, printHands);
            stand(player, printHands);
        }
    }

    private void stand(final Player player, final BiConsumer<PlayerName, Hands> printHands) {
        if (player.isHit()) {
            player.stand(printHands);
        }
    }

    public Map<PlayerName, BetRevenue> determineBetRevenue(final State dealerState) {
        final Map<PlayerName, BetRevenue> playerBetRevenue = new LinkedHashMap<>();

        for (final Player player : players) {
            final BetStatus betStatus = BetStatus.of(dealerState, player.getState());
            final BetAmount betAmount = player.getBetAmount();

            playerBetRevenue.put(player.getName(), betStatus.applyLeverage(betAmount));
        }

        return Collections.unmodifiableMap(playerBetRevenue);
    }

    public boolean hasName(final String other) {
        return players.stream()
                .map(Player::getName)
                .anyMatch(name -> name.equals(new PlayerName(other)));
    }

    public boolean isAllBurst() {
        return players.stream()
                .allMatch(Player::isBurst);
    }

    public Map<PlayerName, Hands> getPlayersHands() {
        return players.stream()
                .collect(toMap(Player::getName,
                        Player::getHands,
                        (v1, v2) -> v1,
                        LinkedHashMap::new));
    }

    public List<PlayerName> getNames() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }
}
