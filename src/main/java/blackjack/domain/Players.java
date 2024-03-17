package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.dto.ProfitResult;

import java.math.BigDecimal;
import java.util.*;

public class Players {

    private static final String NAME_DUPLICATED_EXCEPTION = "플레이어의 이름은 중복될 수 없습니다.";

    private final List<Player> players;
    private final Map<Player, Betting> bettingTable;

    private Players(final List<Player> players) {
        this.players = players;
        this.bettingTable = new HashMap<>();
    }

    public static Players of(final List<String> names, final Dealer dealer) {
        validate(names);

        List<Player> players = names.stream()
                .map(name -> new Player(name, dealer))
                .toList();

        return new Players(players);
    }

    private static void validate(final List<String> names) {
        if (isDuplicated(names)) {
            throw new IllegalArgumentException(NAME_DUPLICATED_EXCEPTION);
        }
    }

    private static boolean isDuplicated(final List<String> names) {
        return names.size() != Set.copyOf(names).size();
    }

    public ProfitResult createProfitResult(final Dealer dealer, final BettingTable bettingTable) {
        ProfitResult profitResult = new ProfitResult();

        for (Player player : players) {
            GameResult gameResult = new Judge(dealer, player).judge();
            BigDecimal profit = bettingTable.calculateProfitByPlayer(player, gameResult);
            profitResult.addProfitResult(player, profit);
        }

        return profitResult;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
