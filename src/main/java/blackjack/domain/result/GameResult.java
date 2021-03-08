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
            if (isDealerWin(dealer, gamer)) {
                result.put(gamer, gamer.getBettingMoney() * (-1));
                dealerProfit += gamer.getBettingMoney();
            }
            if (isGamerWin(dealer, gamer)) {
                result.put(gamer, gamer.getBettingMoney());
                dealerProfit -= gamer.getBettingMoney();
            }
            if (isGamerWinWithBlackjack(dealer, gamer)) {
                result.put(gamer, (int)(gamer.getBettingMoney() * 1.5));
                dealerProfit -= (int)(gamer.getBettingMoney() * 1.5);
            }
            if (isDraw(dealer, gamer)) {
                result.put(gamer, 0);
            }
        }
        result.put(dealer, dealerProfit);
        return new GameResult(null, result);
    }

    private static boolean isDraw(Dealer dealer, Gamer gamer) {
        return (dealer.isBlackjack() && gamer.isBlackjack())
                || ((((!gamer.isBlackjack() && !dealer.isBlackjack()) && !gamer.isBust()) && !dealer.isBust()) && gamer.calculateScore() == dealer.calculateScore());
    }

    private static boolean isGamerWinWithBlackjack(Dealer dealer, Gamer gamer) {
        return gamer.isBlackjack() && !dealer.isBlackjack();
    }

    private static boolean isGamerWin(Dealer dealer, Gamer gamer) {
        return (((!gamer.isBlackjack() && !dealer.isBlackjack()) && !gamer.isBust()) && dealer.isBust())
                || ((((!gamer.isBlackjack() && !dealer.isBlackjack()) && !gamer.isBust()) && !dealer.isBust()) && gamer.calculateScore() > dealer.calculateScore());
    }

    private static boolean isDealerWin(Dealer dealer, Gamer gamer) {
        return (!gamer.isBlackjack() && dealer.isBlackjack()) || ((!gamer.isBlackjack() && !dealer.isBlackjack()) && gamer.isBust())
                || ((((!gamer.isBlackjack() && !dealer.isBlackjack()) && !gamer.isBust()) && !dealer.isBust()) && gamer.calculateScore() < dealer.calculateScore());
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