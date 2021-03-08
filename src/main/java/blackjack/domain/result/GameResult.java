package blackjack.domain.result;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamer;
import blackjack.domain.player.Player;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameResult {
    private final Map<Player, List<ResultType>> gameResult;
    private Map<Player, Integer> result;

    private GameResult(Map<Player, List<ResultType>> gameResult) {
        this.gameResult = gameResult;
    }

    private GameResult(Map<Player, List<ResultType>> gameResult, Map<Player, Integer> result) {
        this.gameResult = gameResult;
        this.result = result;
    }

    public static GameResult of(Dealer dealer, List<Gamer> gamers) {
        Map<Player, List<ResultType>> result = new LinkedHashMap<>();
        result.put(dealer, new ArrayList<>());
        for (Gamer gamer : gamers) {
            result.put(gamer, new ArrayList<>());
            Map<Player, ResultType> resultPerGamer = ResultType.judgeGameResult(dealer, gamer);
            result.get(gamer).add(resultPerGamer.get(gamer));
            result.get(dealer).add(resultPerGamer.get(dealer));
        }
        return new GameResult(result);
    }

    //리팩토링 필수 (테스트 통과하기 위해 막 짠것)
    public static GameResult getGameResult(Dealer dealer, List<Gamer> gamers) {
        Map<Player, Integer> result = new LinkedHashMap<>();
        int dealerProfit = 0;
        //딜러가 블랙잭인지 체크
        for (Gamer gamer : gamers) {
            double profitRatio = calculateProfitRatio(dealer, gamer);
            result.put(gamer, (int)(gamer.getBettingMoney() * profitRatio));
            dealerProfit -= (int)(gamer.getBettingMoney() * profitRatio);
        }
        result.put(dealer, dealerProfit);
        return new GameResult(null, result);
    }

    private static double calculateProfitRatio(Dealer dealer, Gamer gamer) {
        if (!dealer.isBlackjack() && gamer.isBlackjack()) {
            return 1.5;
        }
        if (dealer.isBlackjack() && gamer.isBlackjack()) {
            return 0;
        }
        if (dealer.isBlackjack() && !gamer.isBlackjack()) {
            return -1.0;
        }
        return calculateExcept(dealer, gamer);
    }

    private static double calculateExcept(Dealer dealer, Gamer gamer) {
        if (gamer.isBust()) {
            return -1.0;
        }
        if (!gamer.isBust() && dealer.isBust()) {
            return 1.0;
        }
        if (gamer.calculateScore() > dealer.calculateScore()) {
            return 1.0;
        }
        if (gamer.calculateScore() == dealer.calculateScore()) {
            return 0;
        }
        return -1.0;
    }

    public List<ResultType> findByPlayer(Player player) {
        return gameResult.get(player);
    }

    public List<ResultType> getDealerResult(Dealer dealer) {
        return gameResult.get(dealer);
    }

    public Map<Player, ResultType> getGamersResult(List<Gamer> gamers) {
        return gamers.stream()
                .collect(Collectors.toMap(gamer -> gamer, gamer -> gameResult.get(gamer).get(0)));
    }

    public int findProfitByPlayer(Player player) {
        return result.get(player);
    }
}