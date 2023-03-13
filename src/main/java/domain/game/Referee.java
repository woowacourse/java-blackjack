package domain.game;

import domain.card.Hand;
import domain.card.Score;
import domain.user.Dealer;
import domain.user.Player;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Referee {
    public static Map<String, Result> judgeTotalPlayerResult(final List<Player> players, final Dealer dealer) {
        Map<String, Result> playerResults = new LinkedHashMap<>();
        for (Player player : players) {
            playerResults.put(player.getName(), judgePlayerResult(player.getHand(), dealer.getHand()));
        }
        return playerResults;
    }

    /*
    [ ] 딜러가 버스트인 경우, 플레이어가 무조건 승리한다
  - [ ] 딜러가 버스트되지 않았고 플레이어가 버스트인 경우, 플레이어의 패배로 판단한다
  - [ ] 딜러와 플레이어가 둘 다 버스트 되지 않았을 경우, 카드 총합이 더 높은 쪽이 승리한다
  - [ ] 딜러와 플레이어가 둘 다 버스트 되지 않았을 경우, 둘의 카드 총합이 같으면 무승부로 판단한다
  플레이어가 블랙잭이고, 딜러가 블랙잭이 아니라면 플레이어의 승리로 판단한다
  딜러가 블랙잭이고 플레이어가 블랙잭이 아니라면 딜러의 승리로 판단한다
  플레이어와 딜러 둘 다 블랙잭인경우, 무승부로 판단한다
     */
    public static Result judgePlayerResult(final Score playerScore, final Score dealerScore) {
        if(dealerScore.isBust()){
            return Result.WIN;
        }
        if(playerScore.isBust() || dealerScore.isGreaterThan(playerScore)){
            return Result.LOSE;
        }
        if(playerScore.isGreaterThan(dealerScore)){
            return Result.WIN;
        }
        return Result.PUSH;
    }

    public static Result judgePlayerResult(final Hand playerHand, final Hand dealerHand){
        if(playerHand.isBlackjack() && dealerHand.isBlackjack()){
            return Result.PUSH;
        }
        if(playerHand.isBlackjack()){
            return Result.WIN;
        }
        if(dealerHand.isBlackjack()){
            return Result.LOSE;
        }
        return judgePlayerResult(playerHand.getSumOfScores(), dealerHand.getSumOfScores());
    }

    public static Map<Result, Integer> judgeTotalDealerResult(final Map<String, Result> playerResults) {
        Map<Result, Integer> totalDealerResult = new HashMap<>();
        playerResults.values().forEach(playerResult -> calculateResultCountOfDealer(totalDealerResult, playerResult));
        return totalDealerResult;
    }

    private static void calculateResultCountOfDealer(final Map<Result, Integer> totalDealerResult,
                                                     final Result playerResult) {
        Result dealerResult = playerResult.getResultOfOpponent();
        int dealerResultCount = increaseResultCount(totalDealerResult, dealerResult);
        totalDealerResult.put(dealerResult, dealerResultCount);
    }

    private static int increaseResultCount(final Map<Result, Integer> totalDealerResult, Result dealerResult) {
        int originalCount = totalDealerResult.getOrDefault(dealerResult, 0);
        return originalCount + 1;
    }
}
