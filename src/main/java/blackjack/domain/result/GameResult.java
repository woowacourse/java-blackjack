package blackjack.domain.result;

import blackjack.domain.Money;
import blackjack.domain.gambler.Dealer;
import blackjack.domain.gambler.Player;
import blackjack.domain.gambler.Players;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public final class GameResult {

    private static final String NULL_USE_EXCEPTION_MESSAGE = "잘못된 인자 - Null 사용";

    private final Map<Player, Money> playerResults;
    private final Money dealerResults;

    public GameResult(Dealer dealer, Players players) {
        validateDealer(dealer);
        validatePlayers(players);
        this.playerResults = Collections.unmodifiableMap(calculatePlayerResults(dealer, players));
        this.dealerResults = calculateDealerResults();
    }

    private void validatePlayers(Players players) {
        if (Objects.isNull(players)) {
            throw new IllegalArgumentException(NULL_USE_EXCEPTION_MESSAGE);
        }
    }

    private void validateDealer(Dealer dealer) {
        if (Objects.isNull(dealer)) {
            throw new IllegalArgumentException(NULL_USE_EXCEPTION_MESSAGE);
        }
    }

    private Map<Player, Money> calculatePlayerResults(Dealer dealer, Players players) {
        return players.getPlayers().stream()
            .collect(Collectors
                .toMap(player -> player, player -> player.getProfitByComparing(dealer),
                    (a1, a2) -> a1, LinkedHashMap::new));
    }

    private Money calculateDealerResults() {
        Money dealerMoney = Money.fromPositive("0");
        for (Money money : this.playerResults.values()) {
            dealerMoney = dealerMoney.add(money);
        }
        return dealerMoney.multiply(-1.0);
    }

    public Map<Player, Money> getPlayerResults() {
        return playerResults;
    }

    public Money getDealerResults() {
        return dealerResults;
    }
}
