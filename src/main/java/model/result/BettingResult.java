package model.result;

import java.util.Map;
import model.participant.Player;
import model.participant.Players;

public class BettingResult {
    private final Map<Player, Integer> betting;

    public BettingResult(Map<Player, Integer> betting) {
        this.betting = betting;
    }

    public void calculatePlayerBettingResult(Players players, ParticipantWinningResult participantWinningResult) {
        for (Player player : players.getPlayers()){
            GameResult gameResult = participantWinningResult.getPlayerGameResult(player);
            computeResultByWinningStatus(player, gameResult);
        }
    }

    public int calculateDealerFinalResult() {
        return -betting.values().stream().mapToInt(i -> i).sum();
    }

    private void computeResultByWinningStatus(Player player, GameResult gameResult) {
        int bettingPrice = betting.get(player);
        if (gameResult == GameResult.LOSE){
            betting.put(player, -bettingPrice);
        }
        if (gameResult == GameResult.DRAW){
            betting.put(player, 0);
        }
        if (gameResult == GameResult.BLACKJACK){
            betting.put(player, bettingPrice * 3/2);
        }
    }

    public Map<Player, Integer> getBetting() {
        return betting;
    }
}

