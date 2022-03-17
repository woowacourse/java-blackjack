package blackjack.domain;

import blackjack.constant.MatchResult;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ScoreBoard {

    private final EnumMap<MatchResult, Integer> dealerMatchResult = new EnumMap(MatchResult.class);
    private final Map<String, MatchResult> playersMatchResult = new LinkedHashMap<>();

    private ScoreBoard(Dealer dealer, List<Player> players) {
        initDealerMatchResult();
        matchEachOthers(dealer, players);
    }

    public static ScoreBoard of(Dealer dealer, List<Player> players) {
        return new ScoreBoard(dealer, players);
    }

    private void initDealerMatchResult() {
        for (MatchResult matchResult : MatchResult.values()) {
            dealerMatchResult.put(matchResult, 0);
        }
    }

    private void matchEachOthers(Dealer dealer, List<Player> players) {
        for (Player player : players) {
            makePlayerMatchResult(dealer, player);
            makeDealerMatchResult(player);
        }
    }

    private void makePlayerMatchResult(Dealer dealer, Player player) {
        MatchResult matchResult = player.match(dealer);
        playersMatchResult.put(player.getName(), matchResult);
    }

    private void makeDealerMatchResult(Player player) {
        MatchResult matchResult = playersMatchResult.get(player.getName()).reverse();
        dealerMatchResult.put(matchResult, dealerMatchResult.get(matchResult) + 1);
    }

    public Map<MatchResult, Integer> getDealerMatchResults() {
        return Collections.unmodifiableMap(dealerMatchResult);
    }

    public Map<String, MatchResult> getPlayersMatchResult() {
        return Collections.unmodifiableMap(playersMatchResult);
    }

    public int findDealerMatchScore(MatchResult matchResult) {
        return dealerMatchResult.get(matchResult);
    }

    public MatchResult findPlayerMatchResult(String playerName) {
        return playersMatchResult.get(playerName);
    }
}
