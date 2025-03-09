package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ParticipantWinningResult {
    private final Map<Player, GameResult> result;

    public static ParticipantWinningResult of(Players players, Dealer dealer) {
        Map<Player, GameResult> result = new HashMap<>();
        for (Player player : players.getPlayers()) {
            result.put(player, checkPlayerWin(dealer, player));
        }
        return new ParticipantWinningResult(result);
    }

    public ParticipantWinningResult(Map<Player, GameResult> result) {
        this.result = result;
    }

    public Map<GameResult, Integer> decideDealerWinning() {
        Map<GameResult, Integer> dealerResult = new HashMap<>();
        for (Player player : result.keySet()){
            GameResult playerGameResult = result.get(player);
            calculateDealerResult(playerGameResult, dealerResult);
        }
        return dealerResult;
    }

    private static GameResult checkPlayerWin(Dealer dealer, Player player) {
        if (player.isBurst()) {
            return GameResult.LOSE;
        }
        if (dealer.isBurst()) {
            return GameResult.WIN;
        }
        return checkResultIfNotBurst(dealer, player);
    }

    private static GameResult checkResultIfNotBurst(Dealer dealer, Player player) {
        int dealerScore = dealer.calculateFinalScore();
        int playerScore = player.calculateFinalScore();

        if (dealerScore > playerScore){
            return GameResult.LOSE;
        }
        if (dealerScore < playerScore){
            return GameResult.WIN;
        }
        return GameResult.DRAW;
    }

    private void calculateDealerResult(GameResult gameResult, Map<GameResult, Integer> dealerResult) {
        if (gameResult == GameResult.WIN){
            updateDealerResult(GameResult.LOSE, dealerResult);
        }
        if (gameResult == GameResult.LOSE){
            updateDealerResult(GameResult.WIN, dealerResult);
        }
        if (gameResult == GameResult.DRAW){
            updateDealerResult(GameResult.DRAW, dealerResult);
        }
    }

    private void updateDealerResult(GameResult gameResult, Map<GameResult, Integer> dealerResult) {
        dealerResult.merge(gameResult, 1, Integer::sum);
    }

    public Map<Player, GameResult> getResult() {
        return Collections.unmodifiableMap(result);
    }
}
