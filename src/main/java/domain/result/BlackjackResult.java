package domain.result;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class BlackjackResult {
    private final Map<Player, MatchCase> playerResultMap = new LinkedHashMap<>();
    private BlackjackResult(Dealer dealer, Players players) {
        calculateMatchResult(dealer, players);
    }

    private void calculateMatchResult(Dealer dealer, Players players) {
        for (Player player : players) {
            determinePlayerResult(dealer, player);
        }
    }

    private void determinePlayerResult(Dealer dealer, Player player) {
        MatchJudge matchJudge = new MatchJudge(dealer, player);
        MatchCase matchCase = matchJudge.judge();
        playerResultMap.put(player, matchCase);
    }

    public static BlackjackResult from(Dealer dealer, Players players) {
        return new BlackjackResult(dealer, players);
    }

    public Map<Player, MatchCase> getPlayerResultMap() {
        return Collections.unmodifiableMap(new LinkedHashMap<>(playerResultMap));
    }
}
