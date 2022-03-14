package blackjack.model;

import blackjack.model.player.Gamers;
import blackjack.model.player.Player;
import java.util.LinkedHashMap;
import java.util.Map;

public class MatchResult {
    private final Map<String, Result> gamersMatchResult;
    private final Map<Result, Integer> dealerMatchResult;

    public MatchResult(Player dealer, Gamers gamers) {
        this.gamersMatchResult = createGamersMatchResult(dealer, gamers);
        this.dealerMatchResult = createDealerMatchResult(dealer, gamers);
    }

    public Map<String, Result> createGamersMatchResult(Player dealer, Gamers gamers) {
        Map<String, Result> gamersMatchResult = new LinkedHashMap<>();
        for (Player gamer : gamers.getValues()) {
            Result result = match(dealer, gamer);
            gamersMatchResult.put(gamer.getName(), result);
        }
        return gamersMatchResult;
    }

    private Result match(Player dealer, Player gamer) {
        return Result.findBy(dealer, gamer);
    }

    private Map<Result, Integer> createDealerMatchResult(Player dealer, Gamers gamers) {
        Map<Result, Integer> dealerMatchResult = initDealerMatchResult();
        for (Player gamer : gamers.getValues()) {
            Result result = match(dealer, gamer);
            dealerMatchResult.merge(result, 1, Integer::sum);
        }
        return dealerMatchResult;
    }

    private Map<Result, Integer> initDealerMatchResult() {
        Map<Result, Integer> matchResult = new LinkedHashMap<>();
        for (Result result : Result.values()) {
            matchResult.put(result, 0);
        }
        return matchResult;
    }

    public Map<Result, Integer> getDealerMatchResult() {
        return dealerMatchResult;
    }

    public Map<String, Result> getGamersMatchResult() {
        return gamersMatchResult;
    }
}
