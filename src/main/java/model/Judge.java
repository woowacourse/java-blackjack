package model;

import java.util.HashMap;
import java.util.Map;

public class Judge {

    public Map<Player, GameResult> decidePlayerWinning(Players players, Dealer dealer){
        Map<Player, GameResult> result = new HashMap<>();
        for (Player player : players.getPlayers()) {
            result.put(player, checkPlayerWin(dealer, player));
        }
        return result;
    }

    public Map<GameResult, Integer> decideDealerWinning(Map<Player, GameResult> playerResult) {
        Map<GameResult, Integer> dealerResult = new HashMap<>();
        for (Player p : playerResult.keySet()){
            calculateDealerResult(playerResult.get(p), dealerResult);
        }
        return dealerResult;
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

    private void calculateDealerResult(GameResult gameResult, Map<GameResult, Integer> dealerResult) {
        if (gameResult == GameResult.WIN){ //TODO : 메서드명 수정 필요
            dealerResult.merge(GameResult.LOSE, 1, Integer::sum);
        }
        if (gameResult == GameResult.LOSE){
            dealerResult.merge(GameResult.WIN, 1, Integer::sum);
        }
        if (gameResult == GameResult.DRAW){
            dealerResult.merge(GameResult.DRAW, 1, Integer::sum);
        }
    }
}
