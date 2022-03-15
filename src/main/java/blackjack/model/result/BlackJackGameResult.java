package blackjack.model.result;

import blackjack.model.player.Players;
import blackjack.model.player.Participant;
import java.util.LinkedHashMap;
import java.util.Map;

public class BlackJackGameResult {
    /*
    private final Map<String, MatchResult> gamersMatchResult;
    private final Map<MatchResult, Integer> dealerMatchResult;

    public BlackJackGameResult(Participant dealer, Players players) {
        this.gamersMatchResult = createGamersMatchResult(dealer, players);
        this.dealerMatchResult = createDealerMatchResult(dealer, players);
    }

    public Map<String, MatchResult> createGamersMatchResult(Participant dealer, Players players) {
        Map<String, MatchResult> gamersMatchResult = new LinkedHashMap<>();
        for (Participant gamer : players.getValues()) {
            MatchResult matchResult = match(dealer, gamer);
            gamersMatchResult.put(gamer.getName(), matchResult);
        }
        return gamersMatchResult;
    }

    private MatchResult match(Participant dealer, Participant gamer) {
        return MatchResult.findBy(dealer, gamer);
    }

    private Map<MatchResult, Integer> createDealerMatchResult(Participant dealer, Players players) {
        Map<MatchResult, Integer> dealerMatchResult = initDealerMatchResult();
        for (Participant gamer : players.getValues()) {
            MatchResult matchResult = match(dealer, gamer);
            dealerMatchResult.merge(matchResult, 1, Integer::sum);
        }
        return dealerMatchResult;
    }

    private Map<MatchResult, Integer> initDealerMatchResult() {
        Map<MatchResult, Integer> matchResult = new LinkedHashMap<>();
        for (MatchResult result : MatchResult.values()) {
            matchResult.put(result, 0);
        }
        return matchResult;
    }

    public Map<MatchResult, Integer> getDealerMatchResult() {
        return dealerMatchResult;
    }

    public Map<String, MatchResult> getGamersMatchResult() {
        return gamersMatchResult;
    }

     */
}
