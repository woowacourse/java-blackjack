package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import model.participant.Dealer;
import model.participant.Player;
import model.participant.Players;

public class ParticipantWinningResult {
    private final Map<Player, GameResult> result;

    public static ParticipantWinningResult of(Players players, Dealer dealer) {
        Map<Player, GameResult> result = new HashMap<>();
        for (Player player : players.getPlayers()) {
            result.put(player, checkPlayerWinningResult(dealer, player));
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

    private static GameResult checkPlayerWinningResult(Dealer dealer, Player player){
        GameResult playerWinningResult = checkPlayerWin(dealer, player);
        if (playerWinningResult == GameResult.WIN && player.checkBlackjack()){
            return GameResult.BLACKJACK;
        }
        return playerWinningResult;
    }

    private static GameResult checkPlayerWin(Dealer dealer, Player player) {
        if (player.isBust()) {
            return GameResult.LOSE;
        }
        if (dealer.isBust()) {
            return GameResult.WIN;
        }
        return checkResultIfNotBust(dealer, player);
    }

    private static GameResult checkResultIfNotBust(Dealer dealer, Player player) {
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
