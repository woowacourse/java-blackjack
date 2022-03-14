package blackjack.model.result;

import blackjack.model.player.Gamers;
import blackjack.model.player.Player;
import java.util.LinkedHashMap;
import java.util.Map;

public class BlackJackGameResult {
    private final Map<String, MatchResult> gamersMatchResult;
    private final Map<MatchResult, Integer> dealerMatchResult;

    public BlackJackGameResult(Player dealer, Gamers gamers) {
        this.gamersMatchResult = createGamersMatchResult(dealer, gamers);
        this.dealerMatchResult = createDealerMatchResult(dealer, gamers);
    }

    public Map<String, MatchResult> createGamersMatchResult(Player dealer, Gamers gamers) {
        Map<String, MatchResult> gamersMatchResult = new LinkedHashMap<>();
        for (Player gamer : gamers.getValues()) {
            MatchResult matchResult = match(dealer, gamer);
            gamersMatchResult.put(gamer.getName(), matchResult);
        }
        return gamersMatchResult;
    }

    private MatchResult match(Player dealer, Player gamer) {
        return MatchResult.findBy(dealer, gamer);
    }

    private Map<MatchResult, Integer> createDealerMatchResult(Player dealer, Gamers gamers) {
        Map<MatchResult, Integer> dealerMatchResult = initDealerMatchResult();
        for (Player gamer : gamers.getValues()) {
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
}
