package domain;

import domain.constant.GamerResult;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class BettingResult {
    private final Map<Name, Money> playersResult;
    private final Money dealerResult;

    public BettingResult(Dealer dealer, Players players) {
        dealerResult = calculateDealerResult(dealer, players);
        playersResult = calculatePlayersResult(dealer, players);
    }

    private Money calculateDealerResult(Dealer dealer, Players players) {
        return players.getPlayers().stream()
                .filter(player -> !GamerResult.DRAW.equals(dealer.judge(player)))
                .map(player -> player.betting(dealer))
                .map(money -> money.multiply(BigDecimal.valueOf(-1)))
                .reduce(Money.ZERO,Money::add);
    }

    private Map<Name, Money> calculatePlayersResult(Dealer dealer, Players players) {
        return players.getPlayers().stream()
                .collect(Collectors.toMap(
                        Player::getName,
                        player -> player.betting(dealer)));
    }

    public Map<Name, Money> getPlayersResult() {
        return Collections.unmodifiableMap(playersResult);
    }

    public Money getDealerResult() {
        return dealerResult;
    }
}
