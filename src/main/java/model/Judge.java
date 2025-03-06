package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Judge {

    public GameResult checkPlayerWin(Dealer dealer, Player player) {
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

    public Map<GameResult, Integer> decideDealerWinning(Dealer dealer, Players players) {
        Map<GameResult, Integer> dealerResult = new HashMap<>();
        for (Player p : players.getPlayers()){
            GameResult playerGameResult = checkPlayerWin(dealer, p);
            decideDealerResult(playerGameResult, dealerResult);
        }
        return dealerResult;
    }

    private void decideDealerResult(GameResult gameResult, Map<GameResult, Integer> dealerResult) {
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
