package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ScoreBoard {

    EnumMap<MatchResult, Integer> dealerMatchResult = new EnumMap(MatchResult.class);
    Map<Player, MatchResult> playersMatchResult = new LinkedHashMap<>();


    public ScoreBoard(Dealer dealer, List<Player> players) {
        initDealerMatchResult();
        matchEachOther(dealer, players);
    }

    private void initDealerMatchResult() {
        for (MatchResult matchResult : MatchResult.values()) {
            dealerMatchResult.put(matchResult, 0);
        }
    }

    private void matchEachOther(Dealer dealer, List<Player> players) {
        for (Player player : players) {
            makeDealerMatchResult(dealer, player);
            makePlayerMatchResult(dealer, player);
        }
    }

    private void makeDealerMatchResult(Dealer dealer, Player player) {
        MatchResult matchResult = dealer.decideMatchResult(player);
        dealerMatchResult.put(matchResult, dealerMatchResult.get(matchResult) + 1);
    }

    private void makePlayerMatchResult(Dealer dealer, Player player) {
        MatchResult matchResult = player.decideMatchResult(dealer);
        playersMatchResult.put(player, matchResult);
    }

    public EnumMap<MatchResult, Integer> getDealerMatchResults() {
        return dealerMatchResult;
    }

    public Map<Player, MatchResult> getPlayersMatchResult() {
        return playersMatchResult;
    }

    public int findDealerMatchScore(MatchResult matchResult) {
        return dealerMatchResult.get(matchResult);
    }
}
