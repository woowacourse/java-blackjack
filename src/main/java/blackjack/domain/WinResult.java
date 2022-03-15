package blackjack.domain;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;

public class WinResult {

    private final Map<Judgement, Integer> dealerResult;
    private final Map<String, Judgement> playersResult;

    public WinResult(Dealer dealer, List<Player> players) {
        this.playersResult = calculatePlayerResult(dealer, players);
        this.dealerResult = calculateDealerResult();
    }

    public Map<String, Judgement> calculatePlayerResult(Dealer dealer, List<Player> players) {
        return players.stream()
            .collect(Collectors.toMap(Participant::getName, player -> Judgement.judgePlayer(player, dealer)));
    }

    public Map<Judgement, Integer> calculateDealerResult() {
        return playersResult.values().stream()
            .collect(Collectors.toMap(Judgement::getOpposite, j -> 1, Integer::sum));
    }

    public Map<Judgement, Integer> getDealerResult() {
        return Collections.unmodifiableMap(dealerResult);
    }

    public Map<String, Judgement> getPlayersResult() {
        return Collections.unmodifiableMap(playersResult);
    }
}
