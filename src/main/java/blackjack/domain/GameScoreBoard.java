package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameScoreBoard {

    private Map<Result, Integer> dealerResultMap = new EnumMap<>(Result.class);
    private Map<String, String> playerResultMap = new HashMap<>();

    public GameScoreBoard(Dealer dealer, List<Player> players) {

        for (Result result : Result.values()) {
            dealerResultMap.put(result, 0);
        }

        for (Player player : players) {
            Result playerResult = player.compareMatchResult(dealer.getCardHand().getScore());
            Result dealerResult = playerResult.reverse();
            dealerResultMap.put(dealerResult, dealerResultMap.get(dealerResult) + 1);
            playerResultMap.put(player.getName(), playerResult.getName());
        }
    }

    public int getDealerWinCount() {
        return dealerResultMap.get(Result.WIN);
    }

    public int getDealerLoseCount() {
        return dealerResultMap.get(Result.LOSE);
    }

    public int getDrawCount() {
        return dealerResultMap.get(Result.DRAW);
    }

    public Map<String, String> getPlayerResultMap() {
        return playerResultMap;
    }
}
