package domain;

import domain.constant.GamerResult;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackResult {
    private final Map<Name, GamerResult> playersResult;
    private final Map<GamerResult, Integer> dealerResult;

    public BlackJackResult(Dealer dealer, Players players) {
        dealerResult = calculateDealerResult(dealer, players);
        this.playersResult = calculatePlayersResult(dealer, players);
    }

    private Map<GamerResult, Integer> calculateDealerResult(Dealer dealer, Players players) {
        return players.getPlayers().stream()
                .collect(Collectors.groupingBy(
                        dealer::judge,
                        Collectors.summingInt(count -> 1)));
    }

    private Map<Name, GamerResult> calculatePlayersResult(Dealer dealer, Players players) {
        return players.getPlayers().stream()
                .collect(Collectors.toMap(
                        Gamer::getName,
                        player -> player.judge(dealer)));
    }

    public Map<Name, GamerResult> getPlayersResult() {
        return Collections.unmodifiableMap(playersResult);
    }

    public Map<GamerResult, Integer> getDealerResult() {
        return Collections.unmodifiableMap(dealerResult);
    }
}
