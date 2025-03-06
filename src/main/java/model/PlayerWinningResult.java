package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PlayerWinningResult {
    private final Map<Player, GameResult> result;//모든 player에 대해서 각자 이겼는지, 졌는지

    public static PlayerWinningResult of(Players players, Dealer dealer) {
        /**
         * player 별로 승패 결과를 저장한다.
         */
        Map<Player, GameResult> result = new HashMap<>();
        for (Player player : players.getPlayers()) {
            result.put(player, checkPlayerWin(dealer, player));
        }
        return new PlayerWinningResult(result);
    }

    public PlayerWinningResult(Map<Player, GameResult> result) {
        this.result = result;
    }

    private static GameResult checkPlayerWin(Dealer dealer, Player player) {
        /**
         * player가 burst되면 무조건 player가 패배한다.
         */
        if (player.getParticipantHand().checkBurst()) {
            return GameResult.LOSE;
        }
        /**
         * player는 burst가 아닐 때 딜러가 burst면 player는 무조건 승리한다.
         */
        if (dealer.getParticipantHand().checkBurst()) {
            return GameResult.WIN;
        }
        return checkResultIfNotBurst(dealer, player);
    }

    private static GameResult checkResultIfNotBurst(Dealer dealer, Player player) {
        int dealerScore = dealer.getParticipantHand().calculateFinalScore();
        int playerScore = player.getParticipantHand().calculateFinalScore();

        if (dealerScore > playerScore){
            return GameResult.LOSE;
        }
        if (dealerScore < playerScore){
            return GameResult.WIN;
        }
        return GameResult.DRAW;
    }

    public Map<GameResult, Integer> decideDealerWinning() {
        Map<GameResult, Integer> dealerResult = new HashMap<>();
        for (Player player : result.keySet()){
            GameResult playerGameResult = result.get(player); //플레이어의 저장된 승패결과
            calculateDealerResult(playerGameResult, dealerResult);
        }
        return dealerResult;
    }

    private void calculateDealerResult(GameResult gameResult, Map<GameResult, Integer> dealerResult) {
        if (gameResult == GameResult.WIN){
            dealerResult.merge(GameResult.LOSE, 1, Integer::sum);
        }
        if (gameResult == GameResult.LOSE){
            dealerResult.merge(GameResult.WIN, 1, Integer::sum);
        }
        if (gameResult == GameResult.DRAW){
            dealerResult.merge(GameResult.DRAW, 1, Integer::sum);
        }
    }

    public Map<Player, GameResult> getResult() {
        return Collections.unmodifiableMap(result);
    }
}
